package com.xudq.api.po;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 冻结表实体
 * @date
 */
public class FrozenPO implements Serializable {
    /**
     * 主键自增id
     */
    private Integer id;

    /**
     * 发送给渠道（或银行）的订单号pay.business_order_id
     */
    private String businessOrderId;

    /**
     * 业务订单号
     */
    private String orderId;

    /**
     * 公司用户id
     */
    private String comUserId;

    /**
     * 支付中心用户id
     */
    private String pcUserId;

    /**
     * 账户主ID取自acm_accoun.account_id,
     * 2000000001:外部商户
     * 5000000001:个人用户
     */
    private Long accountId;

    /**
     * 账户类型：
     *   用户：
     *       100：应付账款-客户-用户现金账户
     *       102 应付账款-客户-用户保证金账户
     *   商户：
     *       800：应付账款-客户-企业现金账户
     *       808：应付账款-客户-商户结算账户
     *       806：其他应收款-客户-商户专用支出账户
     */
    private Short accountType;

    /**
     * 冻结解冻单号
     */
    private String frozenId;

    /**
     * 解冻需要的原冻结单号
     */
    private String orginFrozenId;

    /**
     * 冻结、解冻金额,单位：分
     */
    private Integer amount;

    /**
     * 收款类型：1：冻结，2：解冻
     */
    private Integer type;

    /**
     * 冻结单状态：1：未冻结，2：已冻结
     */
    private Integer frozenStatus;

    /**
     * 解冻时需要原冻结单的金额,单位：分
     */
    private Integer orderAmount;

    /**
     * 冻结时间
     */
    private Date frozenTime;

    /**
     * 资金种类,币种:CNY-人民币:USD-美元
     */
    private String curency;

    /**
     * 用户充值或者付款时的操作IP
     */
    private String sourceIp;

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

    /**
     * 冻结类型  99:其他;1:保证金; 2:提现;
     * */
    private Integer frozenType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrozenStatus() {
        return frozenStatus;
    }

    public void setFrozenStatus(Integer frozenStatus) {
        this.frozenStatus = frozenStatus;
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

    public String getComUserId() {
        return comUserId;
    }

    public void setComUserId(String comUserId) {
        this.comUserId = comUserId;
    }

    public String getPcUserId() {
        return pcUserId;
    }

    public void setPcUserId(String pcUserId) {
        this.pcUserId = pcUserId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Short getAccountType() {
        return accountType;
    }

    public void setAccountType(Short accountType) {
        this.accountType = accountType;
    }

    public String getFrozenId() {
        return frozenId;
    }

    public void setFrozenId(String frozenId) {
        this.frozenId = frozenId;
    }

    public String getOrginFrozenId() {
        return orginFrozenId;
    }

    public void setOrginFrozenId(String orginFrozenId) {
        this.orginFrozenId = orginFrozenId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getFrozenTime() {
        return frozenTime;
    }

    public void setFrozenTime(Date frozenTime) {
        this.frozenTime = frozenTime;
    }

    public String getCurency() {
        return curency;
    }

    public void setCurency(String curency) {
        this.curency = curency;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
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

    public Integer getFrozenType() {
        return frozenType;
    }

    public void setFrozenType(Integer frozenType) {
        this.frozenType = frozenType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}