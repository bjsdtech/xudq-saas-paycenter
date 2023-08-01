package com.xudq.api.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 付款交易VO
 * @date
 */
public class PaymentVO {

    /**
     * 用户id
     */
    private String comUserId;

    /**
     * 商户订单号
     */
    private String businessOrderId;

    /**
     * 业务线标识
     * */
    private String source;

    /**
     * 扣款金额
     * */
    private int amount;
    /**
     * 冻结保证金的单号
     * */
    private String frozenId;
    /**
     * 业务订单号
     */
    private String orderId;

    /**
     * 付款类型：1.普通的提现业务，2.线下打款, 3.手动调账业务类型（不参与对账）
     */
    private Integer paymentType;

    /**
     * 业务类型：01:保证金退款 02：正常付款
     */
    private Integer serviceType;

    /**
     * 付款方式1:线上,2:线下
     */
    private Integer paymentMode;

    /**
     * 收款人姓名
     */
    private String toUserName;

    /**
     * 收款方手机号
     */
    private String toPhone;

    /**
     * 收款方银行卡号
     */
    private String toBankCardNum;

    /**
     * 收款方银行名
     */
    private String toBankName;

    /**
     * 支付渠道id,t_channel.channel_id
     */
    private Integer channelId;

    /**
     * 审批单id
     */
    private String approveId;

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Integer paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public String getToBankCardNum() {
        return toBankCardNum;
    }

    public void setToBankCardNum(String toBankCardNum) {
        this.toBankCardNum = toBankCardNum;
    }

    public String getToBankName() {
        return toBankName;
    }

    public void setToBankName(String toBankName) {
        this.toBankName = toBankName;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getApproveId() {
        return approveId;
    }

    public void setApproveId(String approveId) {
        this.approveId = approveId;
    }

    public String getComUserId() {
        return comUserId;
    }

    public void setComUserId(String comUserId) {
        this.comUserId = comUserId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getFrozenId() {
        return frozenId;
    }

    public void setFrozenId(String frozenId) {
        this.frozenId = frozenId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBusinessOrderId() {
        return businessOrderId;
    }

    public void setBusinessOrderId(String businessOrderId) {
        this.businessOrderId = businessOrderId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
