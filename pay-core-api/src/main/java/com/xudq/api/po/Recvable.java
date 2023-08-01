package com.xudq.api.po;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 收款实体
 * @date
 */
public class Recvable implements Serializable {
    /**
     * 主键自增id
     */
    private Integer id;

    /**
     * 公司用户id
     * */
    private String comUserId;

    /**
     * 账户主ID取自acm_accoun.account_id,2000000001:外部商户 5000000001:个人用户|陈苏洲|2018-01-17
     */
    private Long accountId;

    /**
     * 业务订单号
     */
    private String orderId;

    /**
     * 账户类型：用户：100：应付账款-客户-用户现金账户 102 应付账款-客户-用户保证金账户  商户：800：应付账款-客户-企业现金账户 808：应付账款-客户-商户结算账户 806：其他应收款-客户-商户专用支出账户|陈苏洲|2018-01-17
     */
    private Short accountType;

    /**
     * 收款单号
     */
    private String recvableId;

    /**
     * 收款类型：1：为充值，2：为全部充值支付(即收款金额等于交易总金额), 3：为部分充值支付(收款金额小于交易总金额)。4：手动调帐业务类型|陈苏洲|2018-01-17
     */
    private Integer recvableType;

    /**
     * 收款单状态：1:为待收款, 2:为收款成功, 3:清算成功
     */
    private Integer recvableStatus;

    /**
     * 收款渠道id,t_channel.channel_id
     */
    private Integer channelId;

    /**
     * 收款方式,微信，支付宝等|
     */
    private Integer recvableMode;

    /**
     * 收款来源，paycenter.pay.source|
     */
    private Integer recvableFrom;

    /**
     * 渠道账号acm_account.account_id
     */
    private String channelAccountId;

    /**
     * 充值银行卡号
     */
    private String bankCardNum;

    /**
     * 收款金额,单位：分
     */
    private Integer amount;

    /**
     * 发送给渠道（或银行）的订单号pay.business_order_id
     */
    private String sendChannelTransId;

    /**
     * 渠道（或银行）返回订单号pay.channel_trans_id
     */
    private String channelReturnTransId;

    /**
     * 实际收款时间|
     */
    private Date payTime;

    /**
     * 实际收款时，外部系统时间
     */
    private Date extPayTime;

    /**
     * 资金种类,币种:CNY-人民币:USD-美元
     */
    private String curencyType;

    /**
     * 用户充值或者付款时的操作IP
     */
    private String sourceIp;

    /**
     * 交易单Id,交易表的trans_id
     */
    private String transId;

    /**
     * 交易类型code码
     */
    private String transCode;

    /**
     * 消费金额
     */
    private Integer consumeAmount;

    /**
     * 消费id
     */
    private String consumeId;

    /**
     * 充值人姓名
     */
    private String payerName;

    /**
     * 失败原因
     */
    private String failReason;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getRecvableId() {
        return recvableId;
    }

    public void setRecvableId(String recvableId) {
        this.recvableId = recvableId;
    }

    public Integer getRecvableType() {
        return recvableType;
    }

    public void setRecvableType(Integer recvableType) {
        this.recvableType = recvableType;
    }

    public Integer getRecvableStatus() {
        return recvableStatus;
    }

    public void setRecvableStatus(Integer recvableStatus) {
        this.recvableStatus = recvableStatus;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getRecvableMode() {
        return recvableMode;
    }

    public void setRecvableMode(Integer recvableMode) {
        this.recvableMode = recvableMode;
    }

    public Integer getRecvableFrom() {
        return recvableFrom;
    }

    public void setRecvableFrom(Integer recvableFrom) {
        this.recvableFrom = recvableFrom;
    }

    public String getChannelAccountId() {
        return channelAccountId;
    }

    public void setChannelAccountId(String channelAccountId) {
        this.channelAccountId = channelAccountId;
    }

    public String getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public Integer getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Integer consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public String getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(String consumeId) {
        this.consumeId = consumeId;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}