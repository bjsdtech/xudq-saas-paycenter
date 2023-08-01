package com.xudq.api.controller;


//import com.xudq.common.enums.PreTypeEnum;
//import com.xudq.common.util.NumGenerator;
import com.sun.tools.classfile.ConstantPool;
import com.xudq.api.enums.AccountingType;
import com.xudq.api.enums.AdjustmentStatus;
import com.xudq.api.po.*;
import com.xudq.api.service.*;

import com.xudq.api.utils.NumGenerator;
import com.xudq.api.utils.ValidationUtil;
import com.xudq.api.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 调用调用账务中心(pay-acm-center)查询，操作账户
 * @date
 */
@RestController
@RequestMapping("/pay")
public class AcmController {

    protected static final Logger logger = LoggerFactory.getLogger(AcmController.class);
    public static final String SOURCE_ACCOUNT_ID = "1000001002";
    public static final String SOURCE = "10013";

    @Autowired
    private AcmService acmService;

    @Autowired
    private FrozenService frozenService;

    @Autowired
    private AccountingEntryService accountingEntryService;

    /**
     * 查询、创建个人账户信息
     *
     * @param request
     * @return
     */

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TransferAccountService transferAccountService;

    @Autowired
    private PayCoreAdjustAccountService payCoreAdjustAccountService;

    @Autowired
    private JedisService jedisService;

/*
@RequestMapping(value = "/queryAccount", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<AcmAccountDto>> query(@Valid @RequestBody AcmCreateAccountRequest request, BindingResult bindingResult) {
        logger.info("查询个人账户信息: {}", request);
        try {
            String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
            if (StringUtils.isNotBlank(errMsg)) {
                logger.error("查询个人账户信息失败, 参数验证失败: {}", errMsg);
                return BaseResponse.buildParamFail("查询个人账户信息失败: " + errMsg);
            }

            List<AcmAccountDto> accounts = acmService.queryAccountByComId(request.getComUserId());

            logger.info("查询个人账户信息成功: {}", accounts);
            return BaseResponse.buildSucc("查询个人账户信息成功", accounts);
        } catch (Exception e) {
            logger.error("查询个人账户信息失败: ", e);
            return BaseResponse.buildSysFail("查询个人账户信息失败, " + e.getMessage());
        }
    }*/

    /**
     * 查询、创建个人账户信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryAccountBalance", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<AcmAccountDto>> queryAccountBalance(@Valid @RequestBody AcmCreateAccountRequest request,
                                                                 BindingResult bindingResult) {
        logger.info("查询个人账户信息: {}", request);

        try {
            String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
            if (StringUtils.isNotBlank(errMsg)) {
                logger.error("查询个人账户信息失败, 参数验证失败: {}", errMsg);
                return BaseResponse.buildParamFail("查询个人账户信息失败: " + errMsg);
            }
            return acmService.queryAccountBalance(request.getComUserId(), request.getBuyerPhone());
        } catch (Exception e) {
            logger.error("查询个人账户信息失败: ", e);
            return BaseResponse.buildSysFail("查询个人账户信息失败, " + e.getMessage());
        }
    }


    /**
     * 冻结保证金
     *
     * @param acmFrozenBalance
     * @return
     */
    @RequestMapping(value = "/frozenBalance", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> freezeBalance(@Valid @RequestBody AcmFrozenBalance acmFrozenBalance, BindingResult bindingResult) {
        logger.info("冻结保证金: {}", acmFrozenBalance);
        try {
            String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
            if (StringUtils.isNotBlank(errMsg)) {
                logger.error("冻结保证金, 参数验证失败: {}", errMsg);
                return BaseResponse.buildParamFail("冻结保证金失败: " + errMsg);
            }

            acmFrozenBalance.setType(1);
            FrozenPO frozenPO = frozenService.insert(acmFrozenBalance);
            String accounts = acmService.freezeBalance(acmFrozenBalance, frozenPO);
            frozenService.update(frozenPO);

            logger.info("冻结保证金成功: {}", accounts);
            return BaseResponse.buildSucc("冻结保证金成功", accounts);
        } catch (Exception e) {
            logger.error("冻结保证金失败: ", e);
            return BaseResponse.buildSysFail("冻结保证金失败, " + e.getMessage());
        }
    }


    /**
     * 解冻保证金
     *
     * @param acmFrozenBalance
     * @return
     */
    @RequestMapping(value = "/unfrozenBalance", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> unfrozenBalance(@Valid @RequestBody AcmFrozenBalance acmFrozenBalance, BindingResult bindingResult) {
        logger.info("解冻保证金: {}", acmFrozenBalance);
        try {
            String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
            if (StringUtils.isNotBlank(errMsg)) {
                logger.error("解冻保证金失败, 参数验证失败: {}", errMsg);
                return BaseResponse.buildParamFail("解冻保证金失败: " + errMsg);
            }

            acmFrozenBalance.setType(2);
            FrozenPO frozenPO = frozenService.insertUnFrozenRecord(acmFrozenBalance);
            String accounts = acmService.unfrozenBalance(acmFrozenBalance, frozenPO);
            frozenService.update(frozenPO);

            logger.info("解冻保证金成功: {}", accounts);
            return BaseResponse.buildSucc("解冻保证金成功", accounts);
        } catch (Exception e) {
            logger.error("解冻保证金失败: ", e);
            return BaseResponse.buildSysFail("解冻保证金失败, " + e.getMessage());
        }
    }


    /**
     * 扣款交易
     *
     * @param transferAccountVO
     * @return
     */
    @RequestMapping(value = "/deduct", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> deduct(@Valid @RequestBody TransferAccountVO transferAccountVO, BindingResult bindingResult) {
        logger.info("扣款: {}", transferAccountVO);
        try {
            String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
            if (StringUtils.isNotBlank(errMsg)) {
                logger.error("扣款失败, 参数验证失败: {}", errMsg);
                return BaseResponse.buildParamFail("扣款失败: " + errMsg);
            }

            TransferAccount transferAccount = transferAccountService.insert(transferAccountVO);
            String accounts = acmService.deduct(transferAccount);
            transferAccountService.update(transferAccount);

            logger.info("扣款成功: {}", accounts);
            return BaseResponse.buildSucc("扣款成功", accounts);
        } catch (Exception e) {
            logger.error("扣款失败: ", e);
            return BaseResponse.buildSysFail("扣款失败, " + e.getMessage());
        }
    }


    /**
     * 付款交易
     *
     * @param paymentVO
     * @return
     */
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> payment(@Valid @RequestBody PaymentVO paymentVO, BindingResult bindingResult) {
        logger.info("付款: {}", paymentVO);
        try {
            String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
            if (StringUtils.isNotBlank(errMsg)) {
                logger.error("付款失败, 参数验证失败: {}", errMsg);
                return BaseResponse.buildParamFail("付款失败: " + errMsg);
            }

            Payment payment = paymentService.insert(paymentVO);
            String accounts = acmService.payment(payment);
            //付款单状态：1:未创建付款单(等待汇总),2:为已汇总,3:等待付款,4:表示付款成功,5:表示付款失败,6:表示结算成功
            payment.setPaymentStatus(4);
            Date date = new Date();
            payment.setLastModifyTime(date);
            payment.setPayTime(date);
            paymentService.update(payment);

            logger.info("付款成功: {}", accounts);
            return BaseResponse.buildSucc("付款成功", accounts);
        } catch (Exception e) {
            logger.error("付款失败: ", e);
            return BaseResponse.buildSysFail("付款失败, " + e.getMessage());
        }
    }


    /**
     * 付款结算完成
     * @param paymentVO
     * @return
     * */
    @RequestMapping(value = "/settlePayment", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> settlePayment(@Valid @RequestBody PaymentVO paymentVO, BindingResult bindingResult) {
        logger.info("付款结算: {}", paymentVO);
        try {
            String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
            if (StringUtils.isNotBlank(errMsg)) {
                logger.error("付款结算失败, 参数验证失败: {}", errMsg);
                return BaseResponse.buildParamFail("付款结算失败: " + errMsg);
            }
            // 组装参数，校验该单子是否存在
            Payment payment = paymentService.checkSettlePayment(paymentVO);
            String accounts = acmService.payment(payment);
            //付款单状态：1:未创建付款单(等待汇总),2:为已汇总,3:等待付款,4:表示付款成功,5:表示付款失败,6:表示结算成功
            payment.setPaymentStatus(6);
            paymentService.update(payment);

            logger.info("付款结算成功: {}", accounts);
            return BaseResponse.buildSucc("付款结算成功", accounts);
        } catch (Exception e) {
            logger.error("付款结算失败: ", e);
            return BaseResponse.buildSysFail("付款结算失败, " + e.getMessage());
        }
    }


    /**
     * 更新付款信息
     * @param paymentVO
     * @return
     * */
    @RequestMapping(value = "/updatePaymentInfo", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> updatePayment(@Valid @RequestBody PaymentVO paymentVO, BindingResult bindingResult) {
        logger.info("更新付款信息: {}", paymentVO);
        try {
            String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
            if (StringUtils.isNotBlank(errMsg)) {
                logger.error("更新付款信息失败, 参数验证失败: {}", errMsg);
                return BaseResponse.buildParamFail("更新付款信息失败: " + errMsg);
            }

            // 更新付款信息，不修改状态
            paymentService.updatePaymentInfo(paymentVO);
            logger.info("更新付款信息成功: {}");
            return BaseResponse.buildSucc("更新付款信息成功");
        } catch (Exception e) {
            logger.error("更新付款信息失败: ", e);
            return BaseResponse.buildSysFail("更新付款信息失败, " + e.getMessage());
        }
    }

    /**
     * 账户间互转请求
     * @param transferBetweenAccountsVO
     * @return
     * */
    @RequestMapping(value = "/transferBetweenAccountsRequest", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Map<String,String>> transferBetweenAccountsRequest(@Valid @RequestBody TransferBetweenAccountsVO transferBetweenAccountsVO, BindingResult bindingResult){

        String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
        if (StringUtils.isNotBlank(errMsg)) {
            logger.error("同账户间互转请求失败: {}", errMsg);
            return BaseResponse.buildParamFail("同账户间互转请求失败: " + errMsg);
        }

        logger.info("同账户间互转请求: {}", transferBetweenAccountsVO);
        String busiId = transferBetweenAccountsVO.getBusiId();
        String requestId =  transferBetweenAccountsVO.getRequestId();
        lock(busiId, requestId);

        try {
            Map<String,String> map = new HashMap<>();
            PayCoreAdjustAccount _p = payCoreAdjustAccountService.getByBusiIdAndRequestID(busiId,requestId);
            if(null != _p){
                map.put("adjustmentId",_p.getAdjustmentId());
                return BaseResponse.buildSucc(" 同账户间互转信息请求成功",map);
            }
            transferBetweenAccountsVO.getFromAccountId();
            PayCoreAdjustAccount p = getPayCoreAdjustAccount(transferBetweenAccountsVO,transferBetweenAccountsVO.getAmount(),transferBetweenAccountsVO.getPayServiceType(),21);
            p.setAdjustmentMode(Byte.parseByte(transferBetweenAccountsVO.getAdjustmentMode()));
            p.setAdjustmentType(Byte.parseByte(transferBetweenAccountsVO.getAdjustmentType()));
            p.setAdjustmentFrom(transferBetweenAccountsVO.getAdjustmentFrom());
            p = payCoreAdjustAccountService.insertSelective(p);
            map.put("adjustmentId",p.getAdjustmentId());
            return BaseResponse.buildSucc(" 同账户间互转信息请求成功",map);
        } catch (Exception e) {
            logger.error("查询个人账户信息失败: ", e);
            return BaseResponse.buildSysFail("查询个人账户信息失败, " + e.getMessage());
        }
    }

    /**
     *账户间互转审批
     * @param approvePayCoreAdjustAccount
     * @return
     */
    @RequestMapping(value = "/transferBetweenAccountsConfirm", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Map<String,String>> transferBetweenAccountsConfirm(@Valid @RequestBody ApprovePayCoreAdjustAccount approvePayCoreAdjustAccount, BindingResult bindingResult){
        logger.info("账户间互转审批: {}", approvePayCoreAdjustAccount);
        return getMapBaseResponse(approvePayCoreAdjustAccount, bindingResult);
    }

    /**
     *个人现金长款请求
     * @param perCashLongVO
     * @return
     */
    @RequestMapping(value = "/perCashLongRequest", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Map<String,String>> perCashLongRequest(@Valid @RequestBody PerCashLongVO perCashLongVO, BindingResult bindingResult){
        logger.info("个人现金长款请求: {}", perCashLongVO);
        String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
        if (StringUtils.isNotBlank(errMsg)) {
            logger.error("个人现金长款请求失败: {}", errMsg);
            return BaseResponse.buildParamFail("个人现金长款请求失败: " + errMsg);
        }

        String busiId = perCashLongVO.getBusiId();
        String requestId = perCashLongVO.getRequestId();

        lock(busiId, requestId);

        try {
            Map<String,String> map = new HashMap<>();
            PayCoreAdjustAccount _p = payCoreAdjustAccountService.getByBusiIdAndRequestID(busiId,requestId);
            if(null != _p){
                map.put("adjustmentId",_p.getAdjustmentId());
                return BaseResponse.buildSucc(" 个人现金长款请求",map);
            }

            PayCoreAdjustAccount p = getPayCoreAdjustAccount(perCashLongVO,perCashLongVO.getAmount(),perCashLongVO.getPayServiceType(),21);
            p.setAdjustmentMode(Byte.parseByte(perCashLongVO.getAdjustmentMode()));
            p.setAdjustmentType(Byte.parseByte(perCashLongVO.getAdjustmentType()));
            p.setAdjustmentFrom(perCashLongVO.getAdjustmentFrom());
            p = payCoreAdjustAccountService.insertSelective(p);
            map.put("adjustmentId",p.getAdjustmentId());
            return BaseResponse.buildSucc("个人现金长款请求成功",map);
        } catch (Exception e) {
            logger.error("个人现金长款请求失败: ", e);
            return BaseResponse.buildSysFail("个人现金长款请求失败, " + e.getMessage());
        }
    }

    /**
     *个人现金长款审批
     * @param approvePayCoreAdjustAccount
     * @return
     * */
    @RequestMapping(value = "/perCashLongConfirm", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Map<String,String>> perCashLongConfirm(@Valid @RequestBody ApprovePayCoreAdjustAccount approvePayCoreAdjustAccount, BindingResult bindingResult){

        logger.info("个人现金长款审批: {}", approvePayCoreAdjustAccount);
        return getMapBaseResponse(approvePayCoreAdjustAccount, bindingResult);
    }

    /**
     *个人现金短款请求
     * @param perCashShortVO
     * @return
     */
    @RequestMapping(value = "/perCashShortRequest", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Map<String,String>> perCashShortRequest(@Valid @RequestBody PerCashShortVO perCashShortVO, BindingResult bindingResult){
        logger.info("个人现金短款请求: {}", perCashShortVO);
        String busiId = perCashShortVO.getBusiId();
        String requestId = perCashShortVO.getRequestId();

        String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
        if (StringUtils.isNotBlank(errMsg)) {
            logger.error("个人现金短款请求失败: {}", errMsg);
            return BaseResponse.buildParamFail("个人现金短款请求失败: " + errMsg);
        }

        lock(busiId, requestId);

        try {
            Map<String,String> map = new HashMap<>();
            PayCoreAdjustAccount _p = payCoreAdjustAccountService.getByBusiIdAndRequestID(busiId,requestId);
            if(null != _p){
                map.put("adjustmentId",_p.getAdjustmentId());
                return BaseResponse.buildSucc(" 个人现金短款请求",map);
            }
            PayCoreAdjustAccount p = getPayCoreAdjustAccount(perCashShortVO,perCashShortVO.getAmount(),perCashShortVO.getPayServiceType(),12);
            p.setAdjustmentMode(Byte.parseByte(perCashShortVO.getAdjustmentMode()));
            p.setAdjustmentType(Byte.parseByte(perCashShortVO.getAdjustmentType()));
            p.setAdjustmentFrom(perCashShortVO.getAdjustmentFrom());
            p = payCoreAdjustAccountService.insertSelective(p);
            map.put("adjustmentId",p.getAdjustmentId());
            return BaseResponse.buildSucc("个人现金短款请求成功",map);
        } catch (Exception e) {
            logger.error("个人现金短款请求失败: ", e);
            return BaseResponse.buildSysFail("个人现金短款请求失败, " + e.getMessage());
        }
    }

    /**
     *个人现金短款审批
     * @param approvePayCoreAdjustAccount
     * @return
     * */
    @RequestMapping(value = "/perCashShortConfirm", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<Map<String,String>> perCashShortConfirm(@Valid @RequestBody ApprovePayCoreAdjustAccount approvePayCoreAdjustAccount, BindingResult bindingResult){
        logger.info("个人现金短款审批: {}", approvePayCoreAdjustAccount);
        return getMapBaseResponse(approvePayCoreAdjustAccount, bindingResult);
    }

    private PayCoreAdjustAccount getPayCoreAdjustAccount(@Valid @RequestBody Object perDepositShortVO,String amount,String busiType,int dcFlag) {

        AccountingEntry a = new AccountingEntry();
        a.setBusiType(busiType);
        a.setDcFlag(dcFlag);
        AccountingEntry accountingEntry = accountingEntryService.queryAccountingEntry(a);

        PayCoreAdjustAccount p = new PayCoreAdjustAccount();
        BeanUtils.copyProperties(perDepositShortVO,p);
        p.setAmount(Integer.parseInt(amount));
        String adjustmentId = NumGenerator.generator();
        p.setAdjustmentId(adjustmentId);
        p.setCreateTime(new Date());
        p.setLastModifyTime(new Date());
//        p.setAdjustmentMode(Byte.parseByte());
        if (StringUtils.isEmpty(p.getFromAccountId()) || StringUtils.isEmpty(p.getIntoAccountId())) {
            if (AccountingType.Dr.getValue() == dcFlag) {
                p.setFromAccountId(SOURCE_ACCOUNT_ID);
                p.setFromAccountType(accountingEntry.getAccountType());
            } else {
                p.setIntoAccountId(SOURCE_ACCOUNT_ID);
                p.setIntoAccountType(accountingEntry.getAccountType());
            }
        }
        p.setSource(SOURCE);
        p.setAdjustmentStatus((byte)AdjustmentStatus.CREATE.getId());
        p.setIsDeleted((byte)0);
        return p;
    }

    private BaseResponse<Map<String, String>> getMapBaseResponse(@Valid @RequestBody ApprovePayCoreAdjustAccount approvePayCoreAdjustAccount, BindingResult bindingResult) {
        String adjustmentId = approvePayCoreAdjustAccount.getAdjustmentId();
        String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
        if (StringUtils.isNotBlank(errMsg)) {
            logger.error("审批失败: {}", errMsg);
            return BaseResponse.buildParamFail("审批失败: " + errMsg);
        }

        lock("",adjustmentId);

        PayCoreAdjustAccount payCoreAdjustAccount = payCoreAdjustAccountService.getUnAdjustmentByAdjustmentId(adjustmentId);
        Map<String,String> map = new HashMap<>();
        if(null == payCoreAdjustAccount){
            return BaseResponse.buildSysFail("请勿重复发起请求");
        }

        String account = null;
        try {

            String frozenId = approvePayCoreAdjustAccount.getFrozenId();
            if(StringUtils.isEmpty(frozenId)){
                account = acmService.adjustAccount(payCoreAdjustAccount);
            }else {
                account = acmService.transferAccount(payCoreAdjustAccount, frozenId);
            }

            payCoreAdjustAccount.setApproveId(approvePayCoreAdjustAccount.getApproveId());
            payCoreAdjustAccount.setApproverId(approvePayCoreAdjustAccount.getApproverId());
            payCoreAdjustAccount.setAdjustmentStatus((byte) AdjustmentStatus.SUCCESS.getId());
            payCoreAdjustAccount.setLastModifyTime(new Date());
            payCoreAdjustAccountService.updateByPrimaryKeySelective(payCoreAdjustAccount);

            logger.info("审批成功: {}",account);
            return BaseResponse.buildSucc("审批请求成功",map);
        }catch (Exception e){
            payCoreAdjustAccount.setAdjustmentStatus((byte)AdjustmentStatus.FAIL.getId());
            payCoreAdjustAccount.setLastModifyTime(new Date());

            payCoreAdjustAccountService.updateByPrimaryKeySelective(payCoreAdjustAccount);
            logger.error("审批做账出错",e);
            logger.info("审批做账出错: {}",account);
            return BaseResponse.buildSysFail("审批请求失败");
        }
    }

    private void lock(String busiId, String requestId) {
        boolean lock = jedisService.setNxByKey(busiId +requestId, "LOCK", 2);
        while(!lock){
            logger.info("redis lock fail! {}", busiId +requestId);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                logger.info("sleep error", e);
            }
            lock = jedisService.setNxByKey(busiId +requestId, "LOCK", 3);
        }
    }



}
