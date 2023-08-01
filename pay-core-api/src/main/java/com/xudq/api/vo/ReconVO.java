package com.xudq.api.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.NotNull;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 对账传入的实体
 * @date
 */
public class ReconVO {

    @NotNull(message = "支付单号不能为空")
    private String businessOrderId;
    @NotNull(message = "订单号不能为空")
    private String orderId;
    @NotNull(message = "金额不能为空")
    private String amount;
    @NotNull(message = "创建时间不能为空")
    private String createTime;
    @NotNull(message = "支付时间不能为空")
    private String payTime;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
