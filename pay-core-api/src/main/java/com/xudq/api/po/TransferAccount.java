package com.xudq.api.po;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 转账、扣款交易实体
 * @date
 */
public class TransferAccount implements Serializable {
    /**
     * 主键自增id
     */
    private Integer id;

    /**
     * 转账单号
     */
    private String transferId;

    /**
     * 公司用户id
     * */
    private String comUserId;

    /**
     * 业务订单号
     */
    private String orderId;

    /**
     * 商户订单号pay.business_order_id
     */
    private String businessOrderId;

    /**
     * 冻结保证金的单号
     * */
    private String frozenId;

    /**
     * 收款类型：1：正常转账，2：扣款
     */
    private Integer transferType;

    /**
     * 收款单状态：1:为待转账, 2:为转账成功
     */
    private Integer transferStatus;

    /**
     * 交易主体账户主ID取自acm_accoun.account_id,
     *     2000000001:外部商户
     *     5000000001:个人用户
     */
    private Long fromAccountId;

    /**
     * 交易主体账户类型：
     *   用户：
     *       100：应付账款-客户-用户现金账户
     *       102 应付账款-客户-用户保证金账户
     *   商户：
     *       800：应付账款-客户-企业现金账户
     *       808：应付账款-客户-商户结算账户
     *       806：其他应收款-客户-商户专用支出账户
     */
    private Short fromAccountType;

    /**
     * 交易对手账户主ID取自acm_accoun.account_id,
     *     2000000001:外部商户
     *     5000000001:个人用户
     */
    private Long toAccountId;

    /**
     * 交易对手账户类型：用户：100：应付账款-客户-用户现金账户 102 应付账款-客户-用户保证金账户  商户：800：应付账款-客户-企业现金账户 808：应付账款-客户-商户结算账户 806：其他应收款-客户-商户专用支出账户
     */
    private Short toAccountType;

    /**
     * 转账金额,单位：分
     */
    private Integer amount;

    /**
     * 实际收款时间
     */
    private Date transferTime;

    /**
     * 资金种类,币种:CNY-人民币:USD-美元
     */
    private String curencyType;

    /**
     * 用户发起转账操作IP
     */
    private String sourceIp;

    /**
     * 交易类型code码
     */
    private String transCode;

    /**
     * 物理状态（软删除）：0为有效，1为无效
     */
    private Integer isDeleted;

    /**
     * 最后业务修改时间
     */
    private Date lastModifyTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 保留整数类型1
     */
    private Integer reserveInt1;

    /**
     * 保留字串类型1
     */
    private String reserveChar1;

    private int balanceAmount;

    private int frozenAmount;

    private static final long serialVersionUID = 1L;

    public String getComUserId() {
        return comUserId;
    }

    public void setComUserId(String comUserId) {
        this.comUserId = comUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
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

    public Integer getTransferType() {
        return transferType;
    }

    public void setTransferType(Integer transferType) {
        this.transferType = transferType;
    }

    public Integer getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(Integer transferStatus) {
        this.transferStatus = transferStatus;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public Short getFromAccountType() {
        return fromAccountType;
    }

    public void setFromAccountType(Short fromAccountType) {
        this.fromAccountType = fromAccountType;
    }

    public Short getToAccountType() {
        return toAccountType;
    }

    public void setToAccountType(Short toAccountType) {
        this.toAccountType = toAccountType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    public String getCurencyType() {
        return curencyType;
    }

    public void setCurencyType(String curencyType) {
        this.curencyType = curencyType;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
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

    public String getFrozenId() {
        return frozenId;
    }

    public void setFrozenId(String frozenId) {
        this.frozenId = frozenId;
    }

    public int getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(int balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public int getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(int frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}