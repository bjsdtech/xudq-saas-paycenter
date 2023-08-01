package com.xudq.api.po;

import com.xudq.api.vo.BaseRequest;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.NotNull;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 发起冻结，解冻需要的参数，来自acm.AcmFrozenBalance
 * @date
 */
public class AcmFrozenBalance extends BaseRequest {

    /**
     * 支付中心支付单号
     */
    private String businessOrderId;

    /**
     * 业务订单号
     */
    @NotNull(message = "业务订单号不能为空")
    private String orderId;

    /**
     * 交易核心ID
     */
    private String tansCoreId = "";

    /**
     * 支付核心订单ID
     */
    private String payCoreId;

    /**
     * 公司用户中心用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private String comUserId;

    /**
     * 账户号
     */
    private long accountId;

    /**
     * 账户类型
     */
    private int accountType;

    /**
     * 币种
     */
    private String currency = "CNY";

    /**
     * 所属机构
     */
    private int accountOrg = 1001;

    /**
     * 冻结金额，单位分
     */
    private int frozenAmount;

    /**
     * 冻结金额，单位分
     */
    private int defrozenAmount;

    /**
     * 解冻的时候需要的当时冻结单的id
     */
    private String frozenId;

    /**
     * 收款类型：1：冻结，2：解冻
     */
    private Integer type;

    /**
     * 冻结类型  99:其他;1:保证金; 2:提现;
     * */
    private Integer frozenType;

    /**
     * 解冻审批单Id
     * */
    private String approveId;

    public String getApproveId() {
        return approveId;
    }

    public void setApproveId(String approveId) {
        this.approveId = approveId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getComUserId() {
        return comUserId;
    }

    public void setComUserId(String comUserId) {
        this.comUserId = comUserId;
    }

    public int getDefrozenAmount() {
        return defrozenAmount;
    }

    public void setDefrozenAmount(int defrozenAmount) {
        this.defrozenAmount = defrozenAmount;
    }

    public String getFrozenId() {
        return frozenId;
    }

    public void setFrozenId(String frozenId) {
        this.frozenId = frozenId;
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

    public String getTansCoreId() {
        return tansCoreId;
    }

    public void setTansCoreId(String tansCoreId) {
        this.tansCoreId = tansCoreId;
    }

    public String getPayCoreId() {
        return payCoreId;
    }

    public void setPayCoreId(String payCoreId) {
        this.payCoreId = payCoreId;
    }

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
