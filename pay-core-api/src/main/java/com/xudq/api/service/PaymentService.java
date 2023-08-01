package com.xudq.api.service;

import com.google.common.collect.Lists;
import com.xudq.api.dao.PaymentMapper;
import com.xudq.api.enums.AccountType;
import com.xudq.api.exceptions.BusinessException;
import com.xudq.api.po.Payment;
import com.xudq.api.vo.AcmAccountPayDetail;
import com.xudq.api.vo.FlowRequestVO;
import com.xudq.api.vo.PaymentVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 付款服务，付款交易表的各种操作
 * @date
 */
@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private AcmService acmService;

    /**
     * 流水进入付款表
     */
    public Payment insert(PaymentVO paymentVO) throws Exception {
        logger.info("付款流水进付款表， params = {}", paymentVO);

        //transfer paymentVO to payment
        Payment payment = paymentVO2Payment(paymentVO);
        //未付款
        payment.setPaymentStatus(1);

        // 判断是否重复的key
        Payment paymentHis = queryByPaymentId(paymentVO.getBusinessOrderId());
        if (paymentHis != null) {
            logger.error("businessOrderId已存在 businessOrderId = {}", paymentVO.getBusinessOrderId());
            throw new BusinessException("businessOrderId已存在");
        }

        int result = paymentMapper.insertSelective(payment);

        if(result <= 0){
            logger.error("付款流水进付款表失败");
            throw new Exception("付款流水进付款表失败");
        }
        logger.info("insert payment into db success, params = {}, affact row = {}", payment, result);
        return payment;
    }

    /**
     * 更新付款表的状态
     */
    public void update(Payment payment) throws Exception {
        logger.info("修改付款单状态， params = {}", payment);
        int result = paymentMapper.updateByPrimaryKeySelective(payment);

        if(result <= 0){
            logger.error("修改付款单状态失败");
            throw new Exception("修改付款单状态失败");
        }
        logger.info("update payment into db success, params = {}, affact row = {}", payment, result);
    }



    public void updatePaymentInfo(PaymentVO paymentVO) throws Exception {
        // 限制只能修改 收款人姓名，手机号，开户行，银行卡
        Payment payment = paymentMapper.selectByPaymentId(paymentVO.getBusinessOrderId());
        if(payment == null){
            throw new Exception("没有该付款单");
        }
        payment.setToUserName(paymentVO.getToUserName());
        payment.setToBankCardNum(paymentVO.getToBankCardNum());
        payment.setToPhone(paymentVO.getToPhone());
        payment.setToBankName(paymentVO.getToBankName());
        update(payment);
    }


    /**
     * query payment by paymentId
     */
    public Payment queryByPaymentId(String paymentId){
        Payment payment = paymentMapper.selectByPaymentId(paymentId);
        logger.info("query by paymentId success,params = {}, result = {}", paymentId, payment);
        return payment;
    }


    /**
     * paymentVO 2 Payment
     */
    public Payment paymentVO2Payment(PaymentVO paymentVO){
        //转换实体，填充 Payment
        Payment payment = new Payment();
        payment.setSendChannelTransId(paymentVO.getBusinessOrderId());
        payment.setPaymentId(paymentVO.getBusinessOrderId());
        payment.setOrderId(paymentVO.getOrderId());
        payment.setAmount(paymentVO.getAmount());
        String comUserId = paymentVO.getComUserId();
        //根据source获取业务线信息
        String source = paymentVO.getSource();
        String accountId = acmService.getAccountIdByComId(comUserId);
        payment.setFromAccountId(Long.valueOf(accountId));

        payment.setFromAccountType((short) AccountType.U102.getId());
        payment.setChannelId(paymentVO.getChannelId());
        //分给自己公司的默认 accountId
        payment.setToAccountId((long) 90011003);
        payment.setToAccountType((short) AccountType.B402.getId());
        payment.setTransCode("002");

        payment.setComUserId(comUserId);
        payment.setLastModifyTime(new Date());
        payment.setFrozenId(paymentVO.getFrozenId());

        payment.setPaymentType(paymentVO.getPaymentType());
        payment.setServiceType(paymentVO.getServiceType());
        payment.setPaymentMode(paymentVO.getPaymentMode());
        payment.setToUserName(paymentVO.getToUserName());
        payment.setToPhone(paymentVO.getToPhone());
        payment.setToBankCardNum(paymentVO.getToBankCardNum());
        payment.setToBankName(paymentVO.getToBankName());
        payment.setApproveId(paymentVO.getApproveId());
        return payment;
    }


    /**
     * 查询支付流水付款记录
     */
    public List<Payment> query(FlowRequestVO flowRequestVO) throws Exception {
        logger.info("查询支付流水付款记录， params = {}", flowRequestVO);
        List<Payment> result = paymentMapper.queryPayment(flowRequestVO);
        // 没有的时候返回值是空数组，不是null
        if (result == null  || result.size() <= 0) {
            return Lists.newArrayList();
        }
        List<String> payCoreIds = Lists.transform(result, input -> input.getSendChannelTransId());
        Map<String, Object> voMap = acmService.queryPayDetail(payCoreIds);
        for (Payment payment : result) {
            String key = payment.getSendChannelTransId();
            if (voMap.containsKey(key)) {
                AcmAccountPayDetail detail = (AcmAccountPayDetail) voMap.get(key);
                if (detail.getAccountType() == AccountType.U102.getId()) {
                    payment.setBalanceAmount(detail.getAfterAmount());
                    payment.setFrozenAmount(detail.getAfterFrozenAmount());
                }
            }
        }
        logger.info("query payment from db success, params = {}, result = {}", flowRequestVO, result);
        return result;
    }

    /**
     * 查询支付流水付款记录总条数
     */
    public int queryCount(FlowRequestVO flowRequestVO) throws Exception {
        logger.info("查询支付流水付款记录总条数， params = {}", flowRequestVO);
        int count = paymentMapper.queryPaymentCount(flowRequestVO);
        logger.info("query payment count from db success, params = {}, result = {}", flowRequestVO, count);
        return count;
    }

    /**
     * 查看付款结算单是否存在，以及状态是否合法
     * 返回Payment实体，为后续记账做准备
     */
    public Payment checkSettlePayment(PaymentVO paymentVO) throws Exception {

        Payment payment1 = queryByPaymentId(paymentVO.getBusinessOrderId());
        Payment payment = paymentVO2Payment(paymentVO);
        // TODO payment 跟 payment1 比较,检验合法性
        if(payment1 == null){
            logger.error("没有该付款单");
            throw new Exception("没有该付款单");
        }

        payment.setId(payment1.getId());
        //分给自己公司的默认 accountId
        payment.setFromAccountId((long) 90011003);
        // 应付账款-待结算
        payment.setFromAccountType((short) AccountType.B402.getId());
        payment.setChannelId(paymentVO.getChannelId());
        payment.setToAccountId((long) 90011003);
        //银行存款-归集资金
        payment.setToAccountType((short) AccountType.C302.getId());
        payment.setTransCode("002");
        return payment;
    }
}
