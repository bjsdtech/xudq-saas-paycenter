package com.xudq.api.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;

public class FlowRequestVO {

    private String comUserId;
    private List<String> comUserIds;
    private String buyerPhone;
    private String source;
    private String businessOrderId;
    private String orderId;
    private String startTime;
    private String endTime;
    private Integer page;
    private Integer per;
    private Integer offset;

    /**
     * 状态
     * frozen_status ： 冻结单状态：1：未冻结，2：已冻结
     * transfer_status ： 收款单状态：1:为待转账, 2:为转账成功
     * recvable_status ：收款单状态：1:为待收款, 2:为收款成功, 3:清算成功
     * */
    private Integer status;
    /**
     * 类型：1：冻结，2：解冻
     */
    private Integer type;

    public List<String> getComUserIds() {
        return comUserIds;
    }

    public void setComUserIds(List<String> comUserIds) {
        this.comUserIds = comUserIds;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public int getOffset() {
        return (page - 1) * per;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPer() {
        return per;
    }

    public void setPer(Integer per) {
        this.per = per;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
