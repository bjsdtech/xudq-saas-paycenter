package com.xudq.api.controller;

import com.xudq.api.po.FrozenPO;
import com.xudq.api.po.Payment;
import com.xudq.api.po.Recvable;
import com.xudq.api.po.TransferAccount;
import com.xudq.api.service.AcmService;
import com.xudq.api.service.FrozenService;
import com.xudq.api.service.PaymentService;
import com.xudq.api.service.RecvableService;
import com.xudq.api.service.TransferAccountService;
import com.xudq.api.utils.ValidationUtil;
import com.xudq.api.vo.BaseResponse;
import com.xudq.api.vo.FlowRequestVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 各种查询流水
 * @date
 */

@RestController
@RequestMapping("/flow")
public class FlowController {

    protected static final Logger logger = LoggerFactory.getLogger(FlowController.class);

    @Autowired
    private FrozenService frozenService;

    @Autowired
    private TransferAccountService transferAccountService;

    @Autowired
    private RecvableService recvableService;

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private AcmService acmService;

    /**
     * 查询支付流水冻结记录
     * queryFrozenDetail
     *
     * @param flowRequestVO
     * @return
     */
    @RequestMapping(value = "/queryFrozenDetail", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<FrozenPO>> queryFrozenDetail(@Valid @RequestBody FlowRequestVO flowRequestVO, BindingResult bindingResult) {
        logger.info("查询支付流水冻结记录: {}", flowRequestVO);
        try {
            String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
            if (StringUtils.isNotBlank(errMsg)) {
                logger.error("查询支付流水冻结记录失败, 参数验证失败: {}", errMsg);
                return BaseResponse.buildParamFail("查询支付流水冻结记录失败: " + errMsg);
            }

            // 标识是成功冻结流水
            flowRequestVO.setType(1);
            flowRequestVO.setStatus(2);
            // query comUserIds by buyerPhone
            queryComUserIdsByBuyerPhone(flowRequestVO);
            List<FrozenPO> frozenPOs = frozenService.query(flowRequestVO);
            int count = frozenService.queryCount(flowRequestVO);

            logger.info("查询支付流水冻结记录成功: {}", frozenPOs);
            return BaseResponse.buildSucc(frozenPOs, count);
        } catch (Exception e) {
            logger.error("查询支付流水冻结记录失败: ", e);
            return BaseResponse.buildSysFail("查询支付流水冻结记录失败, " + e.getMessage());
        }
    }

    /**
     * 查询支付流水解冻记录
     * queryUnFrozenDetail
     *
     * @param flowRequestVO
     * @return
     */
    @RequestMapping(value = "/queryUnFrozenDetail", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<FrozenPO>> queryUnFrozenDetail(@Valid @RequestBody FlowRequestVO flowRequestVO, BindingResult bindingResult) {
        logger.info("查询支付流水解冻记录: {}", flowRequestVO);
        try {
            String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
            if (StringUtils.isNotBlank(errMsg)) {
                logger.error("查询支付流水解冻记录失败, 参数验证失败: {}", errMsg);
                return BaseResponse.buildParamFail("查询支付流水解冻记录失败: " + errMsg);
            }

            // 标识是成功解冻流水
            flowRequestVO.setType(2);
            flowRequestVO.setStatus(2);
            // query comUserIds by buyerPhone
            queryComUserIdsByBuyerPhone(flowRequestVO);
            List<FrozenPO> frozenPOs = frozenService.query(flowRequestVO);
            int count = frozenService.queryCount(flowRequestVO);

            logger.info("查询支付流水解冻记录成功: {}", frozenPOs);
            return BaseResponse.buildSucc(frozenPOs, count);
        } catch (Exception e) {
            logger.error("查询支付流水解冻记录失败: ", e);
            return BaseResponse.buildSysFail("查询支付流水解冻记录失败, " + e.getMessage());
        }
    }


    /**
     * 查询支付流水扣款记录
     * queryDeductDetail
     *
     * @param flowRequestVO
     * @return
     */
    @RequestMapping(value = "/queryDeductDetail", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<TransferAccount>> queryDeductDetail(@Valid @RequestBody FlowRequestVO flowRequestVO, BindingResult bindingResult) {
        logger.info("查询支付流水扣款记录: {}", flowRequestVO);
        try {
            String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
            if (StringUtils.isNotBlank(errMsg)) {
                logger.error("查询支付流水扣款记录, 参数验证失败: {}", errMsg);
                return BaseResponse.buildParamFail("查询支付流水扣款记录失败: " + errMsg);
            }
            // 收款单状态：1:为待转账, 2:为转账成功
            // 收款类型：1：正常转账，2：扣款
            // 查询扣款成功的单子
            flowRequestVO.setStatus(2);
            flowRequestVO.setType(2);
            // query comUserIds by buyerPhone
            queryComUserIdsByBuyerPhone(flowRequestVO);
            List<TransferAccount> transferAccounts = transferAccountService.query(flowRequestVO);
            int count = transferAccountService.queryCount(flowRequestVO);

            logger.info("查询支付流水扣款记录成功: {}", transferAccounts);
            return BaseResponse.buildSucc(transferAccounts, count);
        } catch (Exception e) {
            logger.error("查询支付流水扣款记录失败: ", e);
            return BaseResponse.buildSysFail("查询支付流水扣款记录失败, " + e.getMessage());
        }
    }


    /**
     * 查询支付流水收款记录
     * queryRecvDetail
     *
     *
     * @param flowRequestVO
     * @return
     */
    @RequestMapping(value = "/queryRecvDetail", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<Recvable>> queryRecvDetail(@Valid @RequestBody FlowRequestVO flowRequestVO, BindingResult bindingResult) {
        logger.info("查询支付流水收款记录: {}", flowRequestVO);
        try {
            String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
            if (StringUtils.isNotBlank(errMsg)) {
                logger.error("查询支付流水收款记录失败, 参数验证失败: {}", errMsg);
                return BaseResponse.buildParamFail("查询支付流水收款记录失败: " + errMsg);
            }

            // 收款单状态：1:为待收款, 2:为收款成功, 3:清算成功
            // TODO 目前查询出来的是收款成功的，未来对账的进pay-core需要变成收款成功 + 清算成功
            flowRequestVO.setStatus(2);
            // query comUserIds by buyerPhone
            queryComUserIdsByBuyerPhone(flowRequestVO);
            List<Recvable> recvables = recvableService.query(flowRequestVO);
            int count = recvableService.queryCount(flowRequestVO);

            logger.info("查询支付流水收款记录成功: {}", recvables);
            return BaseResponse.buildSucc(recvables, count);
        } catch (Exception e) {
            logger.error("查询支付流水收款记录失败: ", e);
            return BaseResponse.buildSysFail("查询支付流水收款记录失败, " + e.getMessage());
        }
    }



    /**
     * 查询付款流水记录
     * queryPaymentDetail
     * @param flowRequestVO
     * @return
     */
    @RequestMapping(value = "/queryPaymentDetail", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<Payment>> queryPaymentDetail(@Valid @RequestBody FlowRequestVO flowRequestVO, BindingResult bindingResult) {
        logger.info("查询付款流水记录: {}", flowRequestVO);
        try {
            String errMsg = ValidationUtil.getSingleErrorMsgFrom(bindingResult);
            if (StringUtils.isNotBlank(errMsg)) {
                logger.error("查询付款流水记录失败, 参数验证失败: {}", errMsg);
                return BaseResponse.buildParamFail("查询付款流水记录失败: " + errMsg);
            }

            //付款单状态：1:未创建付款单(等待汇总),2:为已汇总,3:等待付款,4:表示付款成功,5:表示付款失败,6:表示结算成功
            // TODO 4,6都是成功的
            flowRequestVO.setStatus(4);
            List<Payment> payments = paymentService.query(flowRequestVO);
            int count = paymentService.queryCount(flowRequestVO);

            logger.info("查询付款流水记录成功: {}", payments);
            return BaseResponse.buildSucc(payments, count);
        } catch (Exception e) {
            logger.error("查询付款流水记录失败: ", e);
            return BaseResponse.buildSysFail("查询付款流水记录失败, " + e.getMessage());
        }
    }
    private void queryComUserIdsByBuyerPhone(FlowRequestVO flowRequestVO){
        String buyerPhone = flowRequestVO.getBuyerPhone();
        if(StringUtils.isNotBlank(buyerPhone)){
            List<String> accountIds = acmService.queryAccountsByBuyerPhone(buyerPhone);
            // accountIds为空，查询的时候应该为空集合
            if(accountIds == null || accountIds.size() == 0){
                accountIds.add("null");
            }
            flowRequestVO.setComUserIds(accountIds);
        }
    }

}
