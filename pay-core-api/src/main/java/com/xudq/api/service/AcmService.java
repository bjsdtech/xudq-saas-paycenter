package com.xudq.api.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.xudq.api.enums.AccountType;
import com.xudq.api.po.*;
import com.xudq.api.utils.HttpReturn;
import com.xudq.api.utils.HttpUtil;
import com.xudq.api.utils.OctoUtils;
import com.xudq.api.vo.*;
import com.xudq.api.po.*;
import com.xudq.api.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 调用ACM服务进行账户注册、查询、记账
 * @date
 */
@Service
public class AcmService {

    private static final Logger logger = LoggerFactory.getLogger(AcmService.class);
    public static final int SOURCE = 10013;

    @Value("${octo.host}")
    private String octoHost;

    @Value("${octo.url.acm.queryCreateByComUserId}")
    private String queryCreateByComUserId;

    /**
     * 调用acm冻结金额
     */
    @Value("${octo.url.acm.freezeBalance}")
    private String freezeBalance;

    /**
     * 调用acm解冻金额
     */
    @Value("${octo.url.acm.unfrozenBalance}")
    private String unfrozenBalance;

    /**
     * 调用acm记账的url
     */
    @Value("${octo.url.acm.keepAccounts}")
    private String keepAccountsUrl;

    /**
     * 交易流水的url
     */
    @Value("${octo.url.acm.queryPayDetail}")
    private String queryPayDetail;

    /**
     * 冻结流水的url
     */
    @Value("${octo.url.acm.queryFrozenDetail}")
    private String queryFrozenDetail;

    /**
     * 根据phoneNum查询comUserIds
     */
    @Value("${octo.url.acm.queryComUserIdByPhoneNumb}")
    private String queryComUserIdByPhoneNumb;

    /**
     * 调用ACM查询、创建用户
     *
     * @param comUserId
     * @return 账户信息，包括账户状态，账户余额等
     */
    public List<AcmAccountDto> queryAccountByComId(String comUserId, String buyerPhone) {

        String url = "http://" + octoHost + queryCreateByComUserId + "?_octo=" + OctoUtils.generateJwtToken();
        Map<String, String> params = Maps.newHashMap();
        params.put("comUserId", comUserId);
        if(StringUtils.isNotBlank(buyerPhone)){
            params.put("phoneNumber", buyerPhone);
        }

        String param = JSONObject.toJSONString(params);
        logger.info("调用ACM查询账户信息 url: {}, params: {}", url, param);
        HttpReturn response = HttpUtil.postJSON(url, param);
        if (response == null || response.getStatus() != 200
                || StringUtils.isBlank(response.getText())) {
            logger.error("调用ACM查询账户信息错误: {}", JSONObject.toJSONString(response));
            throw new RuntimeException("调用ACM查询账户信息错误");
        }
        try {
            String text = response.getText();
            JSONObject body = JSONObject.parseObject(text);
            int code = body.getIntValue("status");
            String errMsg = body.getString("errMsg");
            if (code != 0) {
                logger.error("调用ACM查询账户错误: {}", text);
                throw new RuntimeException(errMsg);
            }
            JSONArray data = body.getJSONArray("data");
            List<AcmAccountDto> list = JSONObject.parseArray(data.toJSONString(), AcmAccountDto.class);
            logger.info("调用ACM查询账户信息成功, AcmAccountDto: {}", list);
            return list;
        } catch (Exception e) {
            logger.error("调用ACM查询账户信息异常", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 查询账户保证金余额
     *
     * @param comUserId
     */
    public BaseResponse queryAccountBalance(String comUserId, String buyerPhone) {
        List<AcmAccountDto> acmAccountDtoList = this.queryAccountByComId(comUserId, buyerPhone);
        Map<String, Object> resultMap = Maps.newHashMap();
        for (AcmAccountDto dto : acmAccountDtoList) {
            if (dto.getAccountType() == AccountType.U102.getId()) {
                resultMap.put("balanceAmount", dto.getBalanceAmount());
                resultMap.put("frozenAmount", dto.getFrozenAmount());
                resultMap.put("accountStatus", dto.getAccountStatus());
                resultMap.put("accountStatusDesc", dto.getAccountStatusDesc());
            }
        }
        return BaseResponse.buildSucc(resultMap);
    }


    /**
     * 根据com_user_id获取accountId
     *
     * @param comUserId
     * @return 账户信息，包括账户状态，账户余额等
     * TODO 请求acm查询接口，不通过查询注册接口获取accountId
     */
    public String getAccountIdByComId(String comUserId) {
        List<AcmAccountDto> acmAccountDtos = queryAccountByComId(comUserId, null);
        if (acmAccountDtos.size() <= 0) {
            return "0";
        }
        return String.valueOf(acmAccountDtos.get(0).getAccountId());
    }

    /***
     * /acm/core/freezeBalance
     *  冻结账户余额接口
     * */
    public String freezeBalance(AcmFrozenBalance acmFrozenBalance, FrozenPO frozenPO) {
        acmFrozenBalance.setPayCoreId(frozenPO.getBusinessOrderId());
        acmFrozenBalance.setAccountType(frozenPO.getAccountType());
        acmFrozenBalance.setAccountId(frozenPO.getAccountId());
        acmFrozenBalance.setFrozenType(frozenPO.getFrozenType());

        String url = "http://" + octoHost + freezeBalance + "?_octo=" + OctoUtils.generateJwtToken();

        String param = JSONObject.toJSONString(acmFrozenBalance);
        logger.info("冻结账户余额 url: {}, params: {}", url, param);
        HttpReturn response = HttpUtil.postJSON(url, param);
        if (response == null || response.getStatus() != 200
                || StringUtils.isBlank(response.getText())) {
            logger.error("冻结账户余额错误: {}", JSONObject.toJSONString(response));
            throw new RuntimeException("冻结账户余额错误");
        }
        try {
            String text = response.getText();
            JSONObject body = JSONObject.parseObject(text);
            int code = body.getIntValue("status");
            String errMsg = body.getString("errMsg");
            if (code != 0) {
                logger.error("冻结账户余额错误: {}", text);
                throw new RuntimeException(errMsg);
            }
            JSONObject data = body.getJSONObject("data");
            logger.info("冻结账户余额信息成功, data: {}", data);
            return "SUCCESS";
        } catch (Exception e) {
            logger.error("冻结账户余额异常", e);
            throw new RuntimeException(e.getMessage());
        }
    }


    /***
     * /acm/unfrozenBalance
     * 解冻账户余额接口
     * */
    public String unfrozenBalance(AcmFrozenBalance acmFrozenBalance, FrozenPO frozenPO) {
        acmFrozenBalance.setPayCoreId(frozenPO.getBusinessOrderId());
        acmFrozenBalance.setAccountType(frozenPO.getAccountType());
        acmFrozenBalance.setAccountId(frozenPO.getAccountId());
        // 解冻审批单Id	0:非审批解冻
        acmFrozenBalance.setApproveId("0");
        String url = "http://" + octoHost + unfrozenBalance + "?_octo=" + OctoUtils.generateJwtToken();

        String param = JSONObject.toJSONString(acmFrozenBalance);
        logger.info("解冻账户余额 url: {}, params: {}", url, param);
        HttpReturn response = HttpUtil.postJSON(url, param);
        if (response == null || response.getStatus() != 200
                || StringUtils.isBlank(response.getText())) {
            logger.error("解冻账户余额错误: {}", JSONObject.toJSONString(response));
            throw new RuntimeException("解冻账户余额错误");
        }
        try {
            String text = response.getText();
            JSONObject body = JSONObject.parseObject(text);
            int code = body.getIntValue("status");
            String errMsg = body.getString("errMsg");
            if (code != 0) {
                logger.error("解冻账户余额错误: {}", text);
                throw new RuntimeException(errMsg);
            }
            JSONObject data = body.getJSONObject("data");
            logger.info("解冻账户余额成功, data: {}", data);
            return "SUCCESS";
        } catch (Exception e) {
            logger.error("解冻账户余额异常", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 请求acm执行扣款操作
     */
    public String deduct(TransferAccount transferAccount) {

        //拼请求实体
        AcmKeepAccountRequest acmKeepAccountRequest = new AcmKeepAccountRequest();
        acmKeepAccountRequest.setBusinessOrderId(transferAccount.getBusinessOrderId());
        acmKeepAccountRequest.setOrderId(transferAccount.getOrderId());
        acmKeepAccountRequest.setTansCoreId("");
        acmKeepAccountRequest.setAmount(transferAccount.getAmount());

        acmKeepAccountRequest.setFrozenAmount(transferAccount.getAmount());
        acmKeepAccountRequest.setTransTime(new Date());
        // C2B
        acmKeepAccountRequest.setSource(SOURCE);

        List<AcmKeepAccountPayTradeVO> payTrades = new ArrayList<>();
        // tradeVO
        AcmKeepAccountPayTradeVO tradeVO = new AcmKeepAccountPayTradeVO();
        tradeVO.setPayCoreId(transferAccount.getBusinessOrderId());
        // TODO 暂时写死
        tradeVO.setTransType("003");
        tradeVO.setBusiType("003002");

        tradeVO.setAmount(transferAccount.getAmount());
        tradeVO.setFrozenAmount(transferAccount.getAmount());
        // PayMemberVO
        List<AcmKeepAccountPayMemberVO> owners = new ArrayList<>();
        AcmKeepAccountPayMemberVO owner = new AcmKeepAccountPayMemberVO();
        owner.setAccountId(transferAccount.getFromAccountId());
        owner.setAccountType(transferAccount.getFromAccountType());
        owner.setAmount(transferAccount.getAmount());
        owner.setFrozenAmount(transferAccount.getAmount());
        // 扣款带上frozenId
        owner.setFrozenId(transferAccount.getFrozenId());
        owner.setEntryNo("01");
        owners.add(owner);

        List<AcmKeepAccountPayMemberVO> others = new ArrayList<>();
        AcmKeepAccountPayMemberVO other = new AcmKeepAccountPayMemberVO();

        other.setAccountId(transferAccount.getToAccountId());
        other.setAccountType(transferAccount.getToAccountType());
        other.setAmount(transferAccount.getAmount());
        other.setEntryNo("02");
        others.add(other);

        tradeVO.setPayOwners(owners);
        tradeVO.setPayOthers(others);
        payTrades.add(tradeVO);
        acmKeepAccountRequest.setPayTrades(payTrades);

        return keepAccounts(acmKeepAccountRequest);
    }

    /**
     * 请求acm执行充值操作
     */
    public String recv(List<Recvable> recvables) throws Exception {
        if (recvables == null || recvables.size() <= 0) {
            logger.info("参数错误，recvables为空");
            throw new Exception("参数错误");
        }
        Recvable recvFirst = recvables.get(0);
        String businessOrderId = recvFirst.getSendChannelTransId();
        String orderId = recvFirst.getOrderId();

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
        //TODO 暂时写死 C2B
        acmKeepAccountRequest.setSource(SOURCE);

        List<AcmKeepAccountPayTradeVO> payTrades = new ArrayList<>();
        for (Recvable recv : recvables) {

            String payCoreId = recv.getRecvableId();
            // tradeVO
            AcmKeepAccountPayTradeVO tradeVO = new AcmKeepAccountPayTradeVO();
            tradeVO.setPayCoreId(payCoreId);
            // TODO 暂时写死
            tradeVO.setTransType("001");
            tradeVO.setBusiType("001002");
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

            // 收款渠道暂
            other.setAccountId(Long.valueOf(recv.getChannelAccountId()));
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
        return keepAccounts(acmKeepAccountRequest);
    }

    /**
     * 请求acm发起记账操作
     */
    public String keepAccounts(AcmKeepAccountRequest acmKeepAccountRequest) {

        String url = "http://" + octoHost + keepAccountsUrl + "?_octo=" + OctoUtils.generateJwtToken();

        String param = JSONObject.toJSONString(acmKeepAccountRequest);
        logger.info("扣款操作 url: {}, params: {}", url, param);
        HttpReturn response = HttpUtil.postJSON(url, param);
        if (response == null || response.getStatus() != 200
                || StringUtils.isBlank(response.getText())) {
            logger.error("扣款操作错误: {}", JSONObject.toJSONString(response));
            throw new RuntimeException("扣款操作错误");
        }
        try {
            String text = response.getText();
            JSONObject body = JSONObject.parseObject(text);
            int code = body.getIntValue("status");
            String errMsg = body.getString("errMsg");
            if (code != 0) {
                logger.error("扣款操作错误: {}", text);
                throw new RuntimeException(errMsg);
            }
            JSONObject data = body.getJSONObject("data");
            logger.info("扣款操作成功, data: {}", data);
            return "SUCCESS";
        } catch (Exception e) {
            logger.error("扣款操作异常", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 请求acm交易流水
     */
    public Map<String, Object> queryPayDetail(List<String> payCoreIds) {

        String url = "http://" + octoHost + queryPayDetail + "?_octo=" + OctoUtils.generateJwtToken();

        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("payCoreIds", payCoreIds);
        logger.info("查询交易流水url: {}, params: {}", url, paramMap);
        HttpReturn response = HttpUtil.postJSON(url, JSONObject.toJSONString(paramMap));
        if (response == null || response.getStatus() != 200
                || StringUtils.isBlank(response.getText())) {
            logger.error("查询交易流水: {}", JSONObject.toJSONString(response));
            throw new RuntimeException("查询交易流水报错");
        }
        try {
            String text = response.getText();
            JSONObject body = JSONObject.parseObject(text);
            int code = body.getIntValue("status");
            String errMsg = body.getString("errMsg");
            if (code != 0) {
                logger.error("查询交易流水: {}", text);
                throw new RuntimeException(errMsg);
            }
            JSONArray data = body.getJSONArray("data");
            List<AcmAccountPayVO> acmAccountPayVOList = data.toJavaList(AcmAccountPayVO.class);
            logger.info("查询交易流水, data: {}", acmAccountPayVOList);
            return convert2Map(acmAccountPayVOList);
        } catch (Exception e) {
            logger.error("查询交易流水", e);
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 请求acm冻结和解冻流水操作
     */
    public Map<String, Object> queryFrozenDetail(List<String> payCoreIds) {

        String url = "http://" + octoHost + queryFrozenDetail + "?_octo=" + OctoUtils.generateJwtToken();

        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("payCoreIds", payCoreIds);
        logger.info("查询冻结解冻流水url: {}, params: {}", url, paramMap);
        HttpReturn response = HttpUtil.postJSON(url, JSONObject.toJSONString(paramMap));
        if (response == null || response.getStatus() != 200
                || StringUtils.isBlank(response.getText())) {
            logger.error("查询冻结解冻流水: {}", JSONObject.toJSONString(response));
            throw new RuntimeException("查询冻结解冻流水报错");
        }
        try {
            String text = response.getText();
            JSONObject body = JSONObject.parseObject(text);
            int code = body.getIntValue("status");
            String errMsg = body.getString("errMsg");
            if (code != 0) {
                logger.error("查询冻结解冻流水: {}", text);
                throw new RuntimeException(errMsg);
            }
            JSONArray data = body.getJSONArray("data");
            List<AcmAccountPayVO> acmAccountPayVOList = data.toJavaList(AcmAccountPayVO.class);
            logger.info("查询冻结解冻流水, data: {}", acmAccountPayVOList);
            return convert2Map(acmAccountPayVOList);
        } catch (Exception e) {
            logger.error("查询冻结解冻流水", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public Map<String, Object> convert2Map(List<AcmAccountPayVO> acmAccountPayVOList) {
        Map<String, Object> result = Maps.newHashMap();
        for (AcmAccountPayVO acmAccountPayVO : acmAccountPayVOList) {
            List<AcmAccountPayDetail> acmAccountPayDetailList = acmAccountPayVO.getDetails();
            acmAccountPayDetailList.stream().filter(e -> e.getAccountType() == AccountType.U102.getId()).forEach(infoTmp -> result.put(infoTmp.getPayCoreId(), infoTmp));
        }
        return result;
    }


    /**
     * 请求acm执行付款操作
     */
    public String payment(Payment payment) {

        //拼请求实体
        AcmKeepAccountRequest acmKeepAccountRequest = new AcmKeepAccountRequest();
        acmKeepAccountRequest.setBusinessOrderId(payment.getSendChannelTransId());
        acmKeepAccountRequest.setOrderId(payment.getOrderId());
        acmKeepAccountRequest.setTansCoreId("");
        acmKeepAccountRequest.setAmount(payment.getAmount());

        acmKeepAccountRequest.setFrozenAmount(payment.getAmount());
        acmKeepAccountRequest.setTransTime(new Date());
        acmKeepAccountRequest.setSource(SOURCE);

        List<AcmKeepAccountPayTradeVO> payTrades = new ArrayList<>();
        // tradeVO
        AcmKeepAccountPayTradeVO tradeVO = new AcmKeepAccountPayTradeVO();
        tradeVO.setPayCoreId(payment.getSendChannelTransId());
        tradeVO.setTransType("002");


        tradeVO.setAmount(payment.getAmount());
        tradeVO.setFrozenAmount(payment.getAmount());
        // PayMemberVO
        List<AcmKeepAccountPayMemberVO> owners = new ArrayList<>();
        AcmKeepAccountPayMemberVO owner = new AcmKeepAccountPayMemberVO();
        owner.setAccountId(payment.getFromAccountId());
        owner.setAccountType(payment.getFromAccountType());
        owner.setAmount(payment.getAmount());
        // 付款对应俩交易码，所以根据交易主体判断使用哪个交易码
        if((short) AccountType.U102.getId() == payment.getFromAccountType()){
            tradeVO.setBusiType("002009");
            owner.setFrozenAmount(payment.getAmount());
            // 扣款带上frozenId
            owner.setFrozenId(payment.getFrozenId());
        } else if((short) AccountType.B402.getId() == payment.getFromAccountType()){
            tradeVO.setStep(2);
            tradeVO.setBusiType("002010");
        }

        owner.setEntryNo("01");
        owners.add(owner);

        List<AcmKeepAccountPayMemberVO> others = new ArrayList<>();
        AcmKeepAccountPayMemberVO other = new AcmKeepAccountPayMemberVO();

        other.setAccountId(payment.getToAccountId());
        other.setAccountType(payment.getToAccountType());
        other.setAmount(payment.getAmount());
        other.setEntryNo("02");
        others.add(other);

        tradeVO.setPayOwners(owners);
        tradeVO.setPayOthers(others);
        payTrades.add(tradeVO);
        acmKeepAccountRequest.setPayTrades(payTrades);

        return keepAccounts(acmKeepAccountRequest);
    }

    /*
     *
     * 根据phoneNum查询comUserIds
     * **/
    public List<String> queryAccountsByBuyerPhone(String buyerPhone) {

        String url = "http://" + octoHost + queryComUserIdByPhoneNumb + "?_octo=" + OctoUtils.generateJwtToken();

        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("phoneNumber", buyerPhone);
        logger.info("根据phoneNum查询 comUserIdsurl: {}, params: {}", url, paramMap);
        HttpReturn response = HttpUtil.postJSON(url, JSONObject.toJSONString(paramMap));
        if (response == null || response.getStatus() != 200
                || StringUtils.isBlank(response.getText())) {
            logger.error("根据phoneNum查询 comUserIds: {}", JSONObject.toJSONString(response));
            throw new RuntimeException("根据phoneNum查询 comUserIds报错");
        }
        try {
            String text = response.getText();
            JSONObject body = JSONObject.parseObject(text);
            int code = body.getIntValue("status");
            String errMsg = body.getString("errMsg");
            if (code != 0) {
                logger.error("根据phoneNum查询 comUserIds: {}", text);
                throw new RuntimeException(errMsg);
            }
            JSONArray data = body.getJSONArray("data");
            List<String> accountIds = data.toJavaList(String.class);
            logger.info("根据phoneNum查询 comUserIds, data: {}", accountIds);
            return accountIds;
        } catch (Exception e) {
            logger.error("根据phoneNum查询 com UserIds", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public String adjustAccount(PayCoreAdjustAccount payCoreAdjustAccount) {
        //拼请求实体
        AcmKeepAccountRequest acmKeepAccountRequest = new AcmKeepAccountRequest();
        acmKeepAccountRequest.setBusinessOrderId(payCoreAdjustAccount.getAdjustmentId());
        acmKeepAccountRequest.setOrderId(payCoreAdjustAccount.getAdjustmentId());
        acmKeepAccountRequest.setTansCoreId("");
        acmKeepAccountRequest.setAmount(payCoreAdjustAccount.getAmount());

//        acmKeepAccountRequest.setFrozenAmount(payCoreAdjustAccount.getAmount());
        acmKeepAccountRequest.setTransTime(new Date());
        // C2B
        acmKeepAccountRequest.setSource(SOURCE);

        List<AcmKeepAccountPayTradeVO> payTrades = new ArrayList<>();
        // tradeVO
        AcmKeepAccountPayTradeVO tradeVO = new AcmKeepAccountPayTradeVO();
        tradeVO.setPayCoreId(payCoreAdjustAccount.getAdjustmentId());

        tradeVO.setTransType(payCoreAdjustAccount.getPayServiceType().substring(0,3));
        tradeVO.setBusiType(payCoreAdjustAccount.getPayServiceType());

        tradeVO.setAmount(payCoreAdjustAccount.getAmount());
//        tradeVO.setFrozenAmount(payCoreAdjustAccount.getAmount());
        // PayMemberVO
        List<AcmKeepAccountPayMemberVO> owners = new ArrayList<>();
        AcmKeepAccountPayMemberVO owner = new AcmKeepAccountPayMemberVO();
        owner.setAccountId(Long.parseLong(payCoreAdjustAccount.getFromAccountId()));
        owner.setAccountType(Short.parseShort(payCoreAdjustAccount.getFromAccountType()));
        owner.setAmount(payCoreAdjustAccount.getAmount());
//        owner.setFrozenAmount(payCoreAdjustAccount.getAmount());
        // 扣款带上frozenId
//        owner.setFrozenId(frozenId);
        owner.setEntryNo("01");
        owners.add(owner);

        List<AcmKeepAccountPayMemberVO> others = new ArrayList<>();
        AcmKeepAccountPayMemberVO other = new AcmKeepAccountPayMemberVO();

        other.setAccountId(Long.parseLong(payCoreAdjustAccount.getIntoAccountId()));
        other.setAccountType(Short.parseShort(payCoreAdjustAccount.getIntoAccountType()));
        other.setAmount(payCoreAdjustAccount.getAmount());
        other.setEntryNo("02");
        others.add(other);

        tradeVO.setPayOwners(owners);
        tradeVO.setPayOthers(others);
        payTrades.add(tradeVO);
        acmKeepAccountRequest.setPayTrades(payTrades);

        return keepAccounts(acmKeepAccountRequest);
    }


    public String transferAccount(PayCoreAdjustAccount payCoreAdjustAccount, String frozenId) {
        //拼请求实体
        AcmKeepAccountRequest acmKeepAccountRequest = new AcmKeepAccountRequest();
        acmKeepAccountRequest.setBusinessOrderId(payCoreAdjustAccount.getAdjustmentId());
        acmKeepAccountRequest.setOrderId(payCoreAdjustAccount.getAdjustmentId());
        acmKeepAccountRequest.setTansCoreId("");
        acmKeepAccountRequest.setAmount(payCoreAdjustAccount.getAmount());

        acmKeepAccountRequest.setFrozenAmount(payCoreAdjustAccount.getAmount());
        acmKeepAccountRequest.setTransTime(new Date());
        // C2B
        acmKeepAccountRequest.setSource(SOURCE);

        List<AcmKeepAccountPayTradeVO> payTrades = new ArrayList<>();
        // tradeVO
        AcmKeepAccountPayTradeVO tradeVO = new AcmKeepAccountPayTradeVO();
        tradeVO.setPayCoreId(payCoreAdjustAccount.getAdjustmentId());

        tradeVO.setTransType(payCoreAdjustAccount.getPayServiceType().substring(0,3));
        tradeVO.setBusiType(payCoreAdjustAccount.getPayServiceType());

        tradeVO.setAmount(payCoreAdjustAccount.getAmount());
        tradeVO.setFrozenAmount(payCoreAdjustAccount.getAmount());
        // PayMemberVO
        List<AcmKeepAccountPayMemberVO> owners = new ArrayList<>();
        AcmKeepAccountPayMemberVO owner = new AcmKeepAccountPayMemberVO();
        owner.setAccountId(Long.parseLong(payCoreAdjustAccount.getFromAccountId()));
        owner.setAccountType(Short.parseShort(payCoreAdjustAccount.getFromAccountType()));
        owner.setAmount(payCoreAdjustAccount.getAmount());
        owner.setFrozenAmount(payCoreAdjustAccount.getAmount());
        // 扣款带上frozenId
        owner.setFrozenId(frozenId);
        owner.setEntryNo("01");
        owners.add(owner);

        List<AcmKeepAccountPayMemberVO> others = new ArrayList<>();
        AcmKeepAccountPayMemberVO other = new AcmKeepAccountPayMemberVO();

        other.setAccountId(Long.parseLong(payCoreAdjustAccount.getIntoAccountId()));
        other.setAccountType(Short.parseShort(payCoreAdjustAccount.getIntoAccountType()));
        other.setAmount(payCoreAdjustAccount.getAmount());
        other.setEntryNo("02");
        others.add(other);

        tradeVO.setPayOwners(owners);
        tradeVO.setPayOthers(others);
        payTrades.add(tradeVO);
        acmKeepAccountRequest.setPayTrades(payTrades);

        return keepAccounts(acmKeepAccountRequest);
    }
}
