package com.xudq.api.po;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 账务(acm)模块的AccountDto，查询acm的个人账户信息的时候的返回值
 * @date
 */
public class AcmAccountDto implements Serializable {

    /**
     * 账户主ID
     *  10000000001:业务线（内部商户）
     *  11000000001:外部商户
     *  50000000001:个人用户
     */
    private long accountId;

    /**
     * 账户类型：
     * 用户：
     *     100：应付账款-客户-用户现金账户
     *     102 应付账款-客户-用户保证金账户
     * 商户：
     *     800：应付账款-客户-企业现金账户
     *     808：应付账款-客户-商户结算账户
     *     806：其他应收款-客户-商户专用支出账户
     */
    private int accountType;

    private String accountTypeDesc;

    /**
     * 资金种类,币种:CNY-人民币:USD-美元
     */
    private String currency;

    /**
     * 账户状态 0-初始,1-正常,2-冻结,3-悬户,4-销户
     */
    private int accountStatus;

    private String accountStatusDesc;

    /**
     * 账户余额=可用金额+冻结金额(单位: 分)
     */
    private int balanceAmount;

    /**
     * 冻结金额
     */
    private int frozenAmount;


    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getAccountTypeDesc() {
        return accountTypeDesc;
    }

    public void setAccountTypeDesc(String accountTypeDesc) {
        this.accountTypeDesc = accountTypeDesc;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountStatusDesc() {
        return accountStatusDesc;
    }

    public void setAccountStatusDesc(String accountStatusDesc) {
        this.accountStatusDesc = accountStatusDesc;
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