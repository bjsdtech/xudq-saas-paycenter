package com.xudq.api.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xudq.api.dao.RecvableMapper;
import com.xudq.api.enums.AccountType;
import com.xudq.api.po.Channel;
import com.xudq.api.po.ChannelMap;
import com.xudq.api.po.Recvable;
import com.xudq.api.service.AcmService;
import com.xudq.api.service.UnityIdService;
import com.xudq.api.vo.AcmKeepAccountPayMemberVO;
import com.xudq.api.vo.AcmKeepAccountPayTradeVO;
import com.xudq.api.vo.AcmKeepAccountRequest;
import com.xudq.api.vo.CleanVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
@Controller
@RequestMapping("/clean")
public class CleaningController {

    private static final Logger logger = LoggerFactory.getLogger(CleaningController.class);

    @Resource
    private AcmService acmService;

    @Resource
    private UnityIdService unityIdService;

    @Resource
    private RecvableMapper recvableMapper;

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public Object cleaningData(@RequestBody MultipartFile file) {
        Map<String, Object> result = Maps.newHashMap();
        try {
            this.cleaning(file);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", -1);
            result.put("msg", "失败");
            return result;
        }
        result.put("code", 0);
        result.put("msg", "成功");
        return result;
    }

    private void cleaning(MultipartFile file) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), "GBK"));
        String line;
        List<CleanVO> cleanVOList = Lists.newArrayList();
        // 跳过第一行
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] data = StringUtils.split(line, ",");
            CleanVO vo = new CleanVO();
            vo.setPhone1(data[0]);
            vo.setName(data[1]);
            vo.setAmount(data[2]);
            vo.setComUserId(data[3]);
            vo.setUserId(data[4]);
            cleanVOList.add(vo);
        }
        // 1 查询并创建账户
        for (CleanVO vo : cleanVOList) {
            acmService.queryAccountByComId(vo.getComUserId(), vo.getPhone1());
        }
        // 2 充值
        Date date = new Date();
        for (CleanVO vo : cleanVOList) {
            List<Recvable> recvableList = this.getRecvables(vo, date);
            recvableMapper.batchInsert(recvableList);
            this.recv(recvableList);
        }
    }

    public List<Recvable> getRecvables(CleanVO cleanVO, Date date) {
        List<Recvable> recvables = Lists.newArrayList();
        //  根据 channel_mer_id获取渠道信息
        String channelMerId = "202202111001936526";
        Channel channel = ChannelMap.getChannel(channelMerId);
        String orderId = UUID.randomUUID().toString().replace("-","").toUpperCase(Locale.ENGLISH);
        // 收款的前缀是1A
        String businessOrderId = "1A" + unityIdService.getUnityId("10013", orderId);
        int rows = recvableMapper.queryCountByBusinessOrderId(businessOrderId);
        if (rows >= 1) {
            logger.info("pay-center存在重复数据 businessOrderId = {}", businessOrderId);
            throw new RuntimeException("pay-center存在重复数据");
        }
        Recvable recvable = new Recvable();
        recvable.setPayTime(date);
        recvable.setExtPayTime(date);
        recvable.setLastModifyTime(date);
        recvable.setOrderId(orderId);
        // TODO: SendChannelTransId值 长度，设置成空串
        recvable.setSendChannelTransId("");
        recvable.setChannelReturnTransId("");
        // 这个value是元，转分
        String amountStr = String.valueOf(cleanVO.getAmount());
        double amountDouble = Double.valueOf(amountStr) * 100;
        DecimalFormat df = new DecimalFormat("#########0"); //四舍五入转换成整数
        amountStr = df.format(amountDouble);
        int amount = Integer.valueOf(amountStr);
        recvable.setAmount(amount);
        // 收款成功标识
        int recvStatus = 2;
        recvable.setRecvableStatus(recvStatus);
        //自己生成的唯一单号
        recvable.setRecvableId(businessOrderId);
        recvable.setCurencyType("CNY");
        recvable.setRecvableType(1);
        String comUserId = cleanVO.getComUserId();
        long accountId = Long.parseLong(acmService.getAccountIdByComId(comUserId));
        recvable.setAccountId(accountId);
        recvable.setComUserId(comUserId);
        recvable.setAccountType((short) AccountType.U102.getId());
        // 交易类型code码
        recvable.setTransCode("002");
        recvable.setPayerName("");
        recvable.setBankCardNum("");
        //// 暂时写死了，需要修改 xudq 20220210
        recvable.setRecvableFrom(10013);
        recvable.setRecvableMode(0);
        recvable.setSourceIp("127.0.0.1");
        recvable.setTransId("");
        recvable.setFailReason("");
        recvable.setChannelId(5001);
        recvable.setChannelAccountId("50011001");
        recvables.add(recvable);
        return recvables;
    }

    /**
     * 请求acm执行充值操作
     */
    public String recv(List<Recvable> recvables) throws Exception {
        Recvable recvable = recvables.get(0);
        String businessOrderId = recvable.getSendChannelTransId();
        String orderId = recvable.getOrderId();
        //拼请求实体
        AcmKeepAccountRequest acmKeepAccountRequest = new AcmKeepAccountRequest();
        acmKeepAccountRequest.setBusinessOrderId(businessOrderId);
        acmKeepAccountRequest.setOrderId(orderId);
        acmKeepAccountRequest.setTansCoreId("");
        //计算所有的recvable.amount sum
        int totalAmount = 0;
        for (Recvable recv : recvables) {
            totalAmount += recv.getAmount();
        }
        acmKeepAccountRequest.setAmount(totalAmount);

        acmKeepAccountRequest.setFrozenAmount(0);
        acmKeepAccountRequest.setTransTime(new Date());
        // 暂时写死了，需要修改 xudq 20220210
        acmKeepAccountRequest.setSource(10013);

        List<AcmKeepAccountPayTradeVO> payTrades = new ArrayList<>();
        for (Recvable recv : recvables) {

            String payCoreId = recv.getRecvableId();
            // tradeVO
            AcmKeepAccountPayTradeVO tradeVO = new AcmKeepAccountPayTradeVO();
            tradeVO.setPayCoreId(payCoreId);
            // TODO 暂时写死 需要修改 xudq 20220210
            tradeVO.setTransType("001");
            tradeVO.setBusiType("001999");
            tradeVO.setAmount(recv.getAmount());
            tradeVO.setFrozenAmount(0);
            // PayMemberVO
            List<AcmKeepAccountPayMemberVO> owners = new ArrayList<>();
            AcmKeepAccountPayMemberVO owner = new AcmKeepAccountPayMemberVO();
            owner.setAccountId(recv.getAccountId());
            owner.setAccountType(recv.getAccountType());
            owner.setAmount(recv.getAmount());
            owner.setFrozenAmount(0);
            owner.setFrozenId("");
            owner.setEntryNo("02");
            owners.add(owner);

            List<AcmKeepAccountPayMemberVO> others = new ArrayList<>();
            AcmKeepAccountPayMemberVO other = new AcmKeepAccountPayMemberVO();

            // TODO 收款渠道暂时写死
            other.setAccountId(50011001);
            other.setAccountType((short) AccountType.C313.getId());
            other.setAmount(recv.getAmount());
            other.setFrozenAmount(0);
            other.setFrozenId("");
            other.setEntryNo("01");
            others.add(other);

            tradeVO.setPayOwners(owners);
            tradeVO.setPayOthers(others);
            payTrades.add(tradeVO);
        }
        acmKeepAccountRequest.setPayTrades(payTrades);
        return acmService.keepAccounts(acmKeepAccountRequest);
    }


    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-",""));

    }
}
