package com.xudq.api.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class AcmKeepAccountRequest extends BaseRequest {

    /**
     * 支付中心支付单号
     */
    @NotNull(message = "支付中心支付单号不能为空")
    private String businessOrderId;

    /**
     * 业务订单号
     */
    @NotNull(message = "业务订单号不能为空")
    private String orderId;

    /**
     * 交易核心ID
     */
    private String tansCoreId = "";

    /**
     * 交易总金额, 单位分
     */
    @Min(value = 1, message = "金额不能小于1")
    private int amount;

    /**
     * 消费冻结金额, 单位分
     */
    @Min(value = 0, message = "冻结金额不能小于0")
    private int frozenAmount;

    /**
     * 发起交易时间
     */
    @NotNull(message = "发起交易时间不能为空")
    private Date transTime;

    /**
     * 业务线
     */
    @Min(value = 0, message = "业务线不能为空")
    private int source;

    /**
     * 支付核心订单
     */
    @NotEmpty(message = "支付核心订单不能为空")
    @Valid
    private List<AcmKeepAccountPayTradeVO> payTrades;


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

    public List<AcmKeepAccountPayTradeVO> getPayTrades() {
        return payTrades;
    }

    public void setPayTrades(List<AcmKeepAccountPayTradeVO> payTrades) {
        this.payTrades = payTrades;
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
