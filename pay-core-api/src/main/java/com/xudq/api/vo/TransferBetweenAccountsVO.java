package com.xudq.api.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 负债类同账户之间转账
 * @date
 */
public class TransferBetweenAccountsVO {

    private String id;

    private String isDeleted;

    private String adjustmentId;
    /*
    调账类型：
           1.客户长款调账，
           2.客户短款调账，
           3.同账户调账，
           4.资金归集，
           5.资金划拨
     */
    private String adjustmentType="3";

    private String adjustmentStatus;

    @Pattern(regexp = "^[1,2]$",message = "调账方式不合法")
    private String adjustmentMode;

    private String payServiceType="035902";
    @NotNull(message = "转出账户ID不能为空")
    private String fromAccountId;

    @Pattern(regexp = "^102$",message = "转出账户类型不合法")
    private String fromAccountType;

    private String fromBankCardNum;

    @Pattern(regexp = "^[1-9]\\d*$",message = "金额不合法")
    private String amount;
    @NotNull(message = "资金种类不能为空")
    private String currencyType;

    @NotNull(message = "转入账户ID不能为空")
    private String intoAccountId;

    @Pattern(regexp = "^102$",message = "转入账户类型不合法")
    private String intoAccountType;

    private String intoBankCardNum;

    @Pattern(regexp = "^[1,2]$",message = "调账平台来源不合法")
    private String adjustmentFrom;

    @NotNull(message = "录入操作员不能为空")
    private String operatorId;

    @NotNull(message = "录入系统时间不能为空")
    private String operateTime;

    private String adjustmentReason;

    private String approverId;

    private String approveTime;

    private String defaultCondCode;

    private String checkIdea;

    private String nodeId;

    private String requestId;

    private String approveId;

    private String sourceIp;

    private String userMac;

    private String createTime;

    private String updateTime;

    private String failReason;

    private String lastModifyTime;

    private String comments;

    private String reserveInt1;

    private String reserveInt2;

    private String reserveChar1;

    private String reserveChar2;

    private String adjustmentReasonType;

    @NotNull(message = "业务线标识不合法")
    private String busiId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(String adjustmentId) {
        this.adjustmentId = adjustmentId == null ? null : adjustmentId.trim();
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getAdjustmentStatus() {
        return adjustmentStatus;
    }

    public void setAdjustmentStatus(String adjustmentStatus) {
        this.adjustmentStatus = adjustmentStatus;
    }

    public String getAdjustmentMode() {
        return adjustmentMode;
    }

    public void setAdjustmentMode(String adjustmentMode) {
        this.adjustmentMode = adjustmentMode;
    }

    public String getPayServiceType() {
        return payServiceType;
    }

    public void setPayServiceType(String payServiceType) {
        this.payServiceType = payServiceType == null ? null : payServiceType.trim();
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId == null ? null : fromAccountId.trim();
    }

    public String getFromAccountType() {
        return fromAccountType;
    }

    public void setFromAccountType(String fromAccountType) {
        this.fromAccountType = fromAccountType == null ? null : fromAccountType.trim();
    }

    public String getFromBankCardNum() {
        return fromBankCardNum;
    }

    public void setFromBankCardNum(String fromBankCardNum) {
        this.fromBankCardNum = fromBankCardNum == null ? null : fromBankCardNum.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType == null ? null : currencyType.trim();
    }

    public String getIntoAccountId() {
        return intoAccountId;
    }

    public void setIntoAccountId(String intoAccountId) {
        this.intoAccountId = intoAccountId == null ? null : intoAccountId.trim();
    }

    public String getIntoAccountType() {
        return intoAccountType;
    }

    public void setIntoAccountType(String intoAccountType) {
        this.intoAccountType = intoAccountType == null ? null : intoAccountType.trim();
    }

    public String getIntoBankCardNum() {
        return intoBankCardNum;
    }

    public void setIntoBankCardNum(String intoBankCardNum) {
        this.intoBankCardNum = intoBankCardNum == null ? null : intoBankCardNum.trim();
    }

    public String getAdjustmentFrom() {
        return adjustmentFrom;
    }

    public void setAdjustmentFrom(String adjustmentFrom) {
        this.adjustmentFrom = adjustmentFrom;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getAdjustmentReason() {
        return adjustmentReason;
    }

    public void setAdjustmentReason(String adjustmentReason) {
        this.adjustmentReason = adjustmentReason == null ? null : adjustmentReason.trim();
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId == null ? null : approverId.trim();
    }

    public String getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime;
    }

    public String getDefaultCondCode() {
        return defaultCondCode;
    }

    public void setDefaultCondCode(String defaultCondCode) {
        this.defaultCondCode = defaultCondCode == null ? null : defaultCondCode.trim();
    }

    public String getCheckIdea() {
        return checkIdea;
    }

    public void setCheckIdea(String checkIdea) {
        this.checkIdea = checkIdea == null ? null : checkIdea.trim();
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    public String getApproveId() {
        return approveId;
    }

    public void setApproveId(String approveId) {
        this.approveId = approveId == null ? null : approveId.trim();
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp == null ? null : sourceIp.trim();
    }

    public String getUserMac() {
        return userMac;
    }

    public void setUserMac(String userMac) {
        this.userMac = userMac == null ? null : userMac.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public String getReserveInt1() {
        return reserveInt1;
    }

    public void setReserveInt1(String reserveInt1) {
        this.reserveInt1 = reserveInt1;
    }

    public String getReserveInt2() {
        return reserveInt2;
    }

    public void setReserveInt2(String reserveInt2) {
        this.reserveInt2 = reserveInt2;
    }

    public String getReserveChar1() {
        return reserveChar1;
    }

    public void setReserveChar1(String reserveChar1) {
        this.reserveChar1 = reserveChar1 == null ? null : reserveChar1.trim();
    }

    public String getReserveChar2() {
        return reserveChar2;
    }

    public void setReserveChar2(String reserveChar2) {
        this.reserveChar2 = reserveChar2 == null ? null : reserveChar2.trim();
    }

    public String getAdjustmentReasonType() {
        return adjustmentReasonType;
    }

    public void setAdjustmentReasonType(String adjustmentReasonType) {
        this.adjustmentReasonType = adjustmentReasonType == null ? null : adjustmentReasonType.trim();
    }

    public String getBusiId() {
        return busiId;
    }

    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    @Override
    public String toString() {
        return "TransferBetweenAccountsVO{" +
                "id=" + id +
                ", isDeleted=" + isDeleted +
                ", adjustmentId='" + adjustmentId + '\'' +
                ", adjustmentType=" + adjustmentType +
                ", adjustmentStatus=" + adjustmentStatus +
                ", adjustmentMode=" + adjustmentMode +
                ", payServiceType='" + payServiceType + '\'' +
                ", fromAccountId='" + fromAccountId + '\'' +
                ", fromAccountType='" + fromAccountType + '\'' +
                ", fromBankCardNum='" + fromBankCardNum + '\'' +
                ", amount=" + amount +
                ", currencyType='" + currencyType + '\'' +
                ", intoAccountId='" + intoAccountId + '\'' +
                ", intoAccountType='" + intoAccountType + '\'' +
                ", intoBankCardNum='" + intoBankCardNum + '\'' +
                ", adjustmentFrom='" + adjustmentFrom + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", operateTime=" + operateTime +
                ", adjustmentReason='" + adjustmentReason + '\'' +
                ", approverId='" + approverId + '\'' +
                ", approveTime=" + approveTime +
                ", defaultCondCode='" + defaultCondCode + '\'' +
                ", checkIdea='" + checkIdea + '\'' +
                ", nodeId='" + nodeId + '\'' +
                ", requestId='" + requestId + '\'' +
                ", approveId='" + approveId + '\'' +
                ", sourceIp='" + sourceIp + '\'' +
                ", userMac='" + userMac + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", failReason='" + failReason + '\'' +
                ", lastModifyTime=" + lastModifyTime +
                ", comments='" + comments + '\'' +
                ", reserveInt1=" + reserveInt1 +
                ", reserveInt2=" + reserveInt2 +
                ", reserveChar1='" + reserveChar1 + '\'' +
                ", reserveChar2='" + reserveChar2 + '\'' +
                ", adjustmentReasonType='" + adjustmentReasonType + '\'' +
                ", busiId='" + busiId + '\'' +
                '}';
    }
}
