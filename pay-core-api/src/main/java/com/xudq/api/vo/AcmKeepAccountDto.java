package com.xudq.api.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

public class AcmKeepAccountDto {

    /**
     * 支付中心支付单号
     */
    private String businessOrderId;

    /**
     * 业务订单号
     */
    private String orderId;

    /**
     * 交易核心ID
     */
    private String tansCoreId = "";

    /**
     * 交易总金额, 单位分
     */
    private int amount;

    /**
     * 消费冻结金额, 单位分
     */
    private int frozenAmount;

    /**
     * 发起交易时间
     */
    private Date transTime;

    /**
     * 业务线
     */
    private int source;

    public String getBusinessOrderId() {
        return businessOrderId;
    }

    public void setBusinessOrderId(String businessOrderId) {
        this.businessOrderId = businessOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTansCoreId() {
        return tansCoreId;
    }

    public void setTansCoreId(String tansCoreId) {
        this.tansCoreId = tansCoreId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(int frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
