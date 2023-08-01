package com.xudq.api.po;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;
/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 付款实体
 * @date
 */
public class Payment implements Serializable {
    /**
     * 主键自增id
     */
    private Integer id;

    /**
     * 付款单号
     */
    private String paymentId;

    /**
     * 冻结单号
     */
    private String frozenId;

    /**
     * 审批单id
     */
    private String approveId;

    /**
     * 业务订单号
     */
    private String orderId;

    /**
     * 汇总单号ID
     */
    private String collectId;

    /**
     * 付款类型：1.普通的提现业务，2.线下打款, 3.手动调账业务类型（不参与对账）
     */
    private Integer paymentType;

    /**
     * 付款单状态：1:未创建付款单(等待汇总),2:为已汇总,3:等待付款,4:表示付款成功,5:表示付款失败,6:表示结算成功
     */
    private Integer paymentStatus;

    /**
     * 业务线ID
     */
    private String busiId;

    /**
     * 付款来源
     */
    private String paymentFrom;

    /**
     * 业务类型：01:保证金退款 02：正常付款
     */
    private Integer serviceType;

    /**
     * 付款方式1:线上,2:线下
     */
    private Integer paymentMode;

    /**
     * 资金种类,币种:CNY-人民币:USD-美元
     */
    private String curencyType;

    /**
     * 收款金额,单位：分
     */
    private Integer amount;

    /**
     * 申请付款金额,单位：分
     */
    private Integer orderAmount;

    /**
     * 发送给渠道(或银行)的订单号
     */
    private String sendChannelTransId;

    /**
     * 渠道(或银行)返回订单号
     */
    private String channelReturnTransId;

    /**
     * 公司用户id
     */
    private String comUserId;

    /**
     * 收款账户,取自acm_accoun.account_id
     */
    private Long toAccountId;

    /**
     * 收款账户的账户类型：用户：100：应付账款-客户-用户现金账户 102 应付账款-客户-用户保证金账户  商户：800：应付账款-客户-企业现金账户 808：应付账款-客户-商户结算账户 806：其他应收款-客户-商户专用支出账户
     */
    private Short toAccountType;

    /**
     * 证件类型:0为身份证,1为护照
     */
    private Short toIdType;

    /**
     * 收款人姓名
     */
    private String toUserName;

    /**
     * 证件号码
     */
    private String toIdNo;

    /**
     * 收款方手机号
     */
    private String toPhone;

    /**
     * 收款方银行卡号
     */
    private String toBankCardNum;

    /**
     * 收款方银行名
     */
    private String toBankName;

    /**
     * 收款方开户行
     */
    private String toOpenAccountBank;

    /**
     * 收款方银行省/市/自治区
     */
    private String toAreaName;

    /**
     * 收款方银行城市
     */
    private String toCityName;

    /**
     * 支付渠道id,t_channel.channel_id
     */
    private Integer channelId;

    /**
     * 付款银行名
     */
    private String channelName;

    /**
     * 付款渠道账户,取自acm_accoun.account_id
     */
    private Long fromAccountId;

    /**
     * 付款渠道的账户类型：
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
     * 交易类型code码
     */
    private String transCode;

    /**
     * 实际收款时间
     */
    private Date payTime;

    /**
     * 实际收款时，外部系统时间
     */
    private Date extPayTime;

    /**
     * 用户付款时的操作IP
     */
    private String sourceIp;

    /**
     * 用户发起提现操作时候使用电脑的MAC地址
     */
    private String userMac;

    /**
     * 失败原因
     */
    private String failReason;

    /**
     * 备注
     */
    private String comments;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getFrozenId() {
        return frozenId;
    }

    public void setFrozenId(String frozenId) {
        this.frozenId = frozenId;
    }

    public String getApproveId() {
        return approveId;
    }

    public void setApproveId(String approveId) {
        this.approveId = approveId;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getBusiId() {
        return busiId;
    }

    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    public String getPaymentFrom() {
        return paymentFrom;
    }

    public void setPaymentFrom(String paymentFrom) {
        this.paymentFrom = paymentFrom;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Integer paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getCurencyType() {
        return curencyType;
    }

    public void setCurencyType(String curencyType) {
        this.curencyType = curencyType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getSendChannelTransId() {
        return sendChannelTransId;
    }

    public void setSendChannelTransId(String sendChannelTransId) {
        this.sendChannelTransId = sendChannelTransId;
    }

    public String getChannelReturnTransId() {
        return channelReturnTransId;
    }

    public void setChannelReturnTransId(String channelReturnTransId) {
        this.channelReturnTransId = channelReturnTransId;
    }

    public String getComUserId() {
        return comUserId;
    }

    public void setComUserId(String comUserId) { this.comUserId = comUserId; }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public Short getToAccountType() {
        return toAccountType;
    }

    public void setToAccountType(Short toAccountType) {
        this.toAccountType = toAccountType;
    }

    public Short getToIdType() {
        return toIdType;
    }

    public void setToIdType(Short toIdType) {
        this.toIdType = toIdType;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getToIdNo() {
        return toIdNo;
    }

    public void setToIdNo(String toIdNo) {
        this.toIdNo = toIdNo;
    }

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public String getToBankCardNum() {
        return toBankCardNum;
    }

    public void setToBankCardNum(String toBankCardNum) {
        this.toBankCardNum = toBankCardNum;
    }

    public String getToBankName() {
        return toBankName;
    }

    public void setToBankName(String toBankName) {
        this.toBankName = toBankName;
    }

    public String getToOpenAccountBank() {
        return toOpenAccountBank;
    }

    public void setToOpenAccountBank(String toOpenAccountBank) {
        this.toOpenAccountBank = toOpenAccountBank;
    }

    public String getToAreaName() {
        return toAreaName;
    }

    public void setToAreaName(String toAreaName) {
        this.toAreaName = toAreaName;
    }

    public String getToCityName() {
        return toCityName;
    }

    public void setToCityName(String toCityName) {
        this.toCityName = toCityName;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Short getFromAccountType() {
        return fromAccountType;
    }

    public void setFromAccountType(Short fromAccountType) {
        this.fromAccountType = fromAccountType;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getExtPayTime() {
        return extPayTime;
    }

    public void setExtPayTime(Date extPayTime) {
        this.extPayTime = extPayTime;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getUserMac() {
        return userMac;
    }

    public void setUserMac(String userMac) {
        this.userMac = userMac;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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