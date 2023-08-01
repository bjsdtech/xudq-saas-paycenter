package com.xudq.api.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AcmKeepAccountPayMemberVO implements Serializable {

    /**
     * 账号
     */
    @Min(value = 1, message = "账号不能为空")
    private long accountId;

    /**
     * 账户类型
     */
    @NotNull(message = "账户类型不能为空")
    private Short accountType;

    /**
     * 币种
     */
    private String currency = "CNY";

    /**
     * 所属机构
     */
    private int accountOrg = 1001;

    /**
     * 交易总金额, 单位分
     */
    @Min(value = 1, message = "金额不能小于1")
    private int amount;

    /**
     * 消费冻结金额, 单位分
     */
    private int frozenAmount;

    /**
     * 冻结单ID
     */
    private String frozenId;

    /**
     * 分录序号
     */
    @NotNull(message = "分录序号不能小于1")
    private String entryNo;


    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public Short getAccountType() {
        return accountType;
    }

    public void setAccountType(Short accountType) {
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

    public String getEntryNo() {
        return entryNo;
    }

    public void setEntryNo(String entryNo) {
        this.entryNo = entryNo;
    }

    public String getFrozenId() {
        return frozenId;
    }

    public void setFrozenId(String frozenId) {
        this.frozenId = frozenId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
