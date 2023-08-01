package com.xudq.api.vo;

import java.io.Serializable;

public class AcmAccountPayDetail implements Serializable {

    /**
     * 账号
     */
    private long accountId;

    /**
     * 账户类型
     */
    private int accountType;

    /**
     * 资金种类,币种:CNY-人民币:USD-美元
     */
    private String currency;

    /**
     * 账务归属机构
     */
    private int accountOrg;

    /**
     * 支付核心的支付id
     */
    private String payCoreId;

    /**
     * 交易类型
     */
    private String transType;

    /**
     * 交易金额
     */
    private int amount;

    /**
     * 交易冻结金额
     */
    private int frozenAmount;

    /**
     * 交易前金额
     */
    private int beforeAmount;

    /**
     * 交易前冻结金额
     */
    private int beforeFrozenAmount;

    /**
     * 交易后金额
     */
    private int afterAmount;

    /**
     * 交易后冻结金额
     */
    private int afterFrozenAmount;

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAccountOrg() {
        return accountOrg;
    }

    public void setAccountOrg(int accountOrg) {
        this.accountOrg = accountOrg;
    }

    public String getPayCoreId() {
        return payCoreId;
    }

    public void setPayCoreId(String payCoreId) {
        this.payCoreId = payCoreId;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(int frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public int getBeforeAmount() {
        return beforeAmount;
    }

    public void setBeforeAmount(int beforeAmount) {
        this.beforeAmount = beforeAmount;
    }

    public int getBeforeFrozenAmount() {
        return beforeFrozenAmount;
    }

    public void setBeforeFrozenAmount(int beforeFrozenAmount) {
        this.beforeFrozenAmount = beforeFrozenAmount;
    }

    public int getAfterAmount() {
        return afterAmount;
    }

    public void setAfterAmount(int afterAmount) {
        this.afterAmount = afterAmount;
    }

    public int getAfterFrozenAmount() {
        return afterFrozenAmount;
    }

    public void setAfterFrozenAmount(int afterFrozenAmount) {
        this.afterFrozenAmount = afterFrozenAmount;
    }

    @Override
    public String toString() {
        return "AcmAccountPayDetail{" +
                "accountId=" + accountId +
                ", accountType=" + accountType +
                ", currency='" + currency + '\'' +
                ", accountOrg=" + accountOrg +
                ", payCoreId=" + payCoreId +
                ", transType='" + transType + '\'' +
                ", amount=" + amount +
                ", frozenAmount=" + frozenAmount +
                ", beforeAmount=" + beforeAmount +
                ", beforeFrozenAmount=" + beforeFrozenAmount +
                ", afterAmount=" + afterAmount +
                ", afterFrozenAmount=" + afterFrozenAmount +
                '}';
    }
}
