package com.xudq.api.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.xudq.api.dao.RecvableHistoryMapper;
import com.xudq.api.dao.RecvableMapper;
import com.xudq.api.enums.AccountType;
import com.xudq.api.po.*;
import com.xudq.api.vo.AcmAccountPayDetail;
import com.xudq.api.vo.FlowRequestVO;
import com.xudq.api.po.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 收款（充值）调用服务，修改流水的状态并发起记账
 * @date
 */
@Service
public class RecvableService {
    private static final Logger logger = LoggerFactory.getLogger(ReconService.class);

    @Autowired
    private RecvableMapper recvableMapper;

    @Autowired
    private RecvableHistoryMapper recvableHistoryMapper;

    @Autowired
    private UnityIdService unityIdService;

    @Autowired
    private AcmService acmService;

    @Resource
    private ChannelService channelService;


    /**
     * 进历史数据表
     */
    public void insertHistory(String params, int times) {
        Map<String, String> recvableMap = JSONObject.parseObject(params, Map.class);
        try {
            // insert message body into history DB
            RecvableHistory recvableHistory = new RecvableHistory();
            recvableHistory.setOrderId(recvableMap.get("order_id"));
            recvableHistory.setBusinessOrderId(recvableMap.get("business_order_id"));
            recvableHistory.setPosData(params);
            recvableHistory.setReconsumeTimes(times);
            logger.info("insert message body into history DB, params : {}", recvableHistory);
            int result = recvableHistoryMapper.insert(recvableHistory);
            logger.info("insert into recvable history success, affect rows : " + result);
        } catch (Exception e) {
            logger.error("insert into recvable history failed, params:" + params, e);
        }
    }

    /**
     * 进收款流水表
     */
    public void insert(String params) throws Exception {
        logger.info("收款流水进入收款表， params = {}", params);
        SettlePO settlePO = JSONObject.parseObject(params, SettlePO.class);
        List<Recvable> recvables = getRecvableList(params, settlePO);
        if (recvables.size() <= 0) {
            logger.error("流水数据非法，没有具体的流水拆分项");
            throw new Exception("流水数据非法，没有具体的流水拆分项");
        }

        // 查询该批次的流水是否已经入库
        FlowRequestVO flowRequestVO = new FlowRequestVO();
        flowRequestVO.setBusinessOrderId(recvables.get(0).getSendChannelTransId());
        int count = recvableMapper.queryRecvablesCount(flowRequestVO);
        if (count > 0) {
            logger.error("流水数据已经差入流水表");
        } else {
            int result = recvableMapper.batchInsert(recvables);
            logger.info("insert recvables into db success, params = {}, affact row = {}", recvables, result);
        }
    }


    public List<Recvable> getRecvableList(String params, SettlePO settlePO) {

        Map<String, Object> map = JSONObject.parseObject(params, Map.class);
        List<Recvable> recvables = Lists.newArrayList();

        //  根据 channel_mer_id获取渠道信息
        String channelMerId = settlePO.getChannel_mer_id();
        //Channel channel1 = ChannelMap.getChannel(channelMerId);
        String channelAccountId = channelService.queryAccountId(channelMerId);

        String orderId = settlePO.getOrder_id();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            FeeType feeType = FeetTypeMap.getFeeType(key);
            if (feeType != null) {
                Recvable recvable = new Recvable();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date payTime = new Date();
                Date updateTime = new Date();
                try {
                    payTime = sdf.parse(settlePO.getPay_time());
                } catch (Exception e) {
                    logger.error("parse time occur exception, params = ", settlePO.getPay_time());
                }
                String businessOrderId = settlePO.getBusiness_order_id();

                recvable.setPayTime(payTime);
                recvable.setExtPayTime(payTime);
                recvable.setLastModifyTime(updateTime);
                recvable.setOrderId(orderId);

                recvable.setSendChannelTransId(businessOrderId);
                recvable.setChannelReturnTransId(settlePO.getSn());
                // 这个value是元，转分
                String amountStr = String.valueOf(value);
                double amountDouble = Double.valueOf(amountStr) * 100;
                DecimalFormat df = new DecimalFormat("#########0"); //四舍五入转换成整数
                amountStr = df.format(amountDouble);
                int amount = Integer.valueOf(amountStr);
                recvable.setAmount(amount);

                //* 收款单状态：1:为待收款, 2:为收款成功, 3:清算成功
                String payStatus = settlePO.getPay_status();
                int recvStatus = 0;
                if ("SUCCESS".equals(payStatus)) {
                    recvStatus = 2;
                    Recvable unpayRecvable = recvableMapper.queryBySendChannelTransId(businessOrderId, AccountType.U102.getId());
                    recvable.setRecvableId(unpayRecvable.getRecvableId());
                } else if ("UNPAY".equals(payStatus)) {
                    recvStatus = 1;
                    //只有初始化的时候才生成唯一单号
                    // 收款的前缀1A
                    recvable.setRecvableId("1A" + unityIdService.getUnityId(settlePO.getSource(), settlePO.getOrder_id()));
                } else if ("SETTLE".equals(payStatus)) {
                    recvStatus = 3;
                    Recvable unpayRecvable = recvableMapper.queryBySendChannelTransId(businessOrderId, AccountType.U102.getId());
                    recvable.setRecvableId(unpayRecvable.getRecvableId());
                }
                recvable.setRecvableStatus(recvStatus);

                recvable.setCurencyType("CNY");
                recvable.setRecvableType(1);

                // 通过com_user_id获取用户的account_id
                String comUserId = settlePO.getCom_user_id();
                long accountId = Long.parseLong(acmService.getAccountIdByComId(comUserId));
                recvable.setAccountId(accountId);
                recvable.setComUserId(comUserId);
                recvable.setChannelAccountId(channelAccountId);
                // TODO
                recvable.setChannelId(Integer.valueOf(StringUtils.left(channelAccountId,4)));

                //TODO 写死了
                //从AccountingEntry表中获取

                List<AccountingEntry> accountingEntries = AccountingEntryMap.getAccountingEntry("001002");
                AccountingEntry tradeConfig = new AccountingEntry();
                for (AccountingEntry accountingEntry : accountingEntries) {
                    if ("21".equals(accountingEntry.getDcFlag().toString())) {
                        tradeConfig = accountingEntry;
                    }
                }

                String busiType = tradeConfig.getBusiType();
                recvable.setAccountType(Short.valueOf(tradeConfig.getAccountType()));
                // 交易类型code码
                recvable.setTransCode("002");
                recvable.setPayerName("");
                recvable.setBankCardNum("");
                recvable.setRecvableFrom(10013);
                recvable.setRecvableMode(0);
                recvable.setSourceIp("127.0.0.1");
                recvable.setTransId("");
                recvable.setFailReason("");

                recvables.add(recvable);
            }
        }
        return recvables;
    }

    public void updateByRecvableId(String params) throws Exception {

        logger.info("修改收款流水状态， params = {}", params);
        SettlePO settlePO = JSONObject.parseObject(params, SettlePO.class);
        // TODO 这个地方跟收款不一样
        List<Recvable> recvables = getRecvableList(params, settlePO);
        if (recvables.size() <= 0) {
            logger.error("流水数据非法，没有具体的流水拆分项");
            throw new Exception("流水数据非法，没有具体的流水拆分项");
        }
        //向acm发起记账
        String recvResult = acmService.recv(recvables);
        logger.info("call acm keep account result = {}", recvables);
        if ("SUCCESS".equals(recvResult)) {
            logger.info("keep account success");
        } else {
            // TODO 异常处理,在recv里面加上状态 ？
            logger.error("keep account failed");
        }
        int result = recvableMapper.batchUpdateByRecvableId(recvables);
        logger.info("update recvables into db success, params = {}, affact row = {}", recvables, result);
        if (result < 1) {
            logger.error("update recvables failed");
            throw new RuntimeException("update recvables failed");
        }
    }


    /**
     * 查询冻结、解冻流水
     */
    public List<Recvable> query(FlowRequestVO flowRequestVO) throws Exception {
        logger.info("查询冻结、解冻流水， params = {}", flowRequestVO);
        List<Recvable> result = recvableMapper.queryRecvables(flowRequestVO);
        // 没有的时候返回值是空数组，不是null
        if (result == null || result.size() <= 0) {
            return Lists.newArrayList();
        }
        List<String> payCoreIds = Lists.transform(result, input -> input.getRecvableId());
        Map<String, Object> voMap = acmService.queryPayDetail(payCoreIds);
        for (Recvable recvable : result) {
            String key = recvable.getRecvableId();
            if (voMap.containsKey(key)) {
                AcmAccountPayDetail detail = (AcmAccountPayDetail) voMap.get(key);
                if (detail.getAccountType() == AccountType.U102.getId()) {
                    recvable.setBalanceAmount(detail.getAfterAmount());
                    recvable.setFrozenAmount(detail.getAfterFrozenAmount());
                }
            }
        }
        logger.info("query frozens from db success, params = {}, result = {}", flowRequestVO, result);
        return result;
    }

    /**
     * 查询冻结、解冻流水条数
     */
    public int queryCount(FlowRequestVO flowRequestVO) throws Exception {
        logger.info("查询冻结、解冻流水条数， params = {}", flowRequestVO);
        int count = recvableMapper.queryRecvablesCount(flowRequestVO);
        logger.info("query frozens count from db success, params = {}, result = {}", flowRequestVO, count);
        return count;
    }

}
