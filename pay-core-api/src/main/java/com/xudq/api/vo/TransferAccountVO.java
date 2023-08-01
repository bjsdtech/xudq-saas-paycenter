package com.xudq.api.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 转账交易VO
 * @date
 */
public class TransferAccountVO {

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
