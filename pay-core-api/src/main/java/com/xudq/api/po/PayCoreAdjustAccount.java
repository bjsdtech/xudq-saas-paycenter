package com.xudq.api.po;

import java.util.Date;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public class PayCoreAdjustAccount {
    private Long id;

    private Byte isDeleted;

    private String adjustmentId;

    private Byte adjustmentType;

    private Byte adjustmentStatus;

    private Byte adjustmentMode;

    private String payServiceType;

    private String fromAccountId;

    private String fromAccountType;

    private String fromBankCardNum;

    private Integer amount;

    private String currencyType;

    private String intoAccountId;

    private String intoAccountType;

    private String intoBankCardNum;

    private String adjustmentFrom;

    private String operatorId;

    private Date operateTime;

    private String adjustmentReason;

    private String approverId;

    private Date approveTime;

    private String defaultCondCode;

    private String checkIdea;

    private String nodeId;

    private String requestId;

    private String approveId;

    private String sourceIp;

    private String userMac;

    private Date createTime;

    private Date updateTime;

    private String failReason;

    private Date lastModifyTime;

    private String comments;

    private Byte reserveInt1;

    private Byte reserveInt2;

    private String reserveChar1;

    private String reserveChar2;

    private String adjustmentReasonType;

    private String busiId;

    private String source;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(String adjustmentId) {
        this.adjustmentId = adjustmentId == null ? null : adjustmentId.trim();
    }

    public Byte getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(Byte adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public Byte getAdjustmentStatus() {
        return adjustmentStatus;
    }

    public void setAdjustmentStatus(Byte adjustmentStatus) {
        this.adjustmentStatus = adjustmentStatus;
    }

    public Byte getAdjustmentMode() {
        return adjustmentMode;
    }

    public void setAdjustmentMode(Byte adjustmentMode) {
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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
        this.adjustmentFrom = adjustmentFrom == null ? null : adjustmentFrom.trim();
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
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

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public Byte getReserveInt1() {
        return reserveInt1;
    }

    public void setReserveInt1(Byte reserveInt1) {
        this.reserveInt1 = reserveInt1;
    }

    public Byte getReserveInt2() {
        return reserveInt2;
    }

    public void setReserveInt2(Byte reserveInt2) {
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
        this.busiId = busiId == null ? null : busiId.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
}