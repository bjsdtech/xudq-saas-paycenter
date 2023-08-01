package com.xudq.api.po;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 会计分录，即交易配置实体
 * @date
 */
public class AccountingEntry implements Serializable {
    /**
     * 主键自增id|
     */
    private Integer id;

    /**
     * 账务归属机构,默认1001|
     */
    private String accountOrg;

    /**
     * 产品码|
     */
    private String productNo;

    /**
     * 交易类型|
     */
    private String transType;

    /**
     * 业务子类型|
     */
    private String busiType;

    /**
     * 分录序号|
     */
    private String entryNo;

    /**
     * 账户种类|
     */
    private String accountType;

    /**
     * 外部账户标志 1：外部账户
     */
    private Integer accountFlag;

    /**
     * 借贷标志@12：借@21：贷|
     */
    private Integer dcFlag;

    /**
     * 会计科目编号|
     */
    private String accountCaptionNo;

    /**
     * 状态@0:初始@1:正常@2:挂起@3:作废|
     */
    private String entryStatus;

    /**
     * 录入操作员|
     */
    private String operatorId;

    /**
     * 录入系统时间|
     */
    private Date operateTime;

    /**
     * 复核操作员|
     */
    private String approverId;

    /**
     * 复核系统时间|
     */
    private Date approveTime;

    /**
     * 节点编号|
     */
    private String nodeId;

    /**
     * 请求编号|
     */
    private String requestId;

    /**
     * 默认特征码|
     */
    private String defaultCondCode;

    /**
     * 审核意见|
     */
    private String checkIdea;

    /**
     * 物理状态（软删除）：0为有效，1为无效|
     */
    private Integer isDeleted;

    /**
     * 最后业务修改时间|
     */
    private Date lastModifyTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间|
     */
    private Date updateTime;

    /**
     * 保留整数类型1|
     */
    private Integer reserveInt1;

    /**
     * 保留字串类型1|
     */
    private String reserveChar1;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountOrg() {
        return accountOrg;
    }

    public void setAccountOrg(String accountOrg) {
        this.accountOrg = accountOrg;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getEntryNo() {
        return entryNo;
    }

    public void setEntryNo(String entryNo) {
        this.entryNo = entryNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Integer getAccountFlag() {
        return accountFlag;
    }

    public void setAccountFlag(Integer accountFlag) {
        this.accountFlag = accountFlag;
    }

    public Integer getDcFlag() {
        return dcFlag;
    }

    public void setDcFlag(Integer dcFlag) {
        this.dcFlag = dcFlag;
    }

    public String getAccountCaptionNo() {
        return accountCaptionNo;
    }

    public void setAccountCaptionNo(String accountCaptionNo) {
        this.accountCaptionNo = accountCaptionNo;
    }

    public String getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getDefaultCondCode() {
        return defaultCondCode;
    }

    public void setDefaultCondCode(String defaultCondCode) {
        this.defaultCondCode = defaultCondCode;
    }

    public String getCheckIdea() {
        return checkIdea;
    }

    public void setCheckIdea(String checkIdea) {
        this.checkIdea = checkIdea;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
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

    public Integer getReserveInt1() {
        return reserveInt1;
    }

    public void setReserveInt1(Integer reserveInt1) {
        this.reserveInt1 = reserveInt1;
    }

    public String getReserveChar1() {
        return reserveChar1;
    }

    public void setReserveChar1(String reserveChar1) {
        this.reserveChar1 = reserveChar1;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}