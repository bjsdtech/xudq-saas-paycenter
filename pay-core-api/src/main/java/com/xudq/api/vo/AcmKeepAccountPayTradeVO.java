package com.xudq.api.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class AcmKeepAccountPayTradeVO implements Serializable {

    /**
     * 支付单号
     */
    @NotNull(message = "支付单号不能为空")
    private String payCoreId;

    /**
     * 支付交易中的子交易
     * 例如：一笔提现交易分为3个子交易: 冻结、转账、付款
     */
    @Min(value = 1, message = "子交易号不能小于1")
    private int step = 1;

    /**
     * 交易类型
     */
    @NotNull(message = "交易类型不能为空")
    private String transType;

    /**
     * 业务子类型
     */
    @NotNull(message = "业务子类型不能为空")
    private String busiType;

    /**
     * 交易总金额, 单位分
     */
    @Min(value = 1, message = "金额不能小于1")
    private int amount;

    /**
     * 消费冻结金额, 单位分
     */
    @Min(value = 0, message = "冻结金额不能小于0")
    private int frozenAmount;

    /**
     * 冲正标记
     */
    private int reversal = 1;

    /**
     * 冲正流水号
     */
    private String reversalTransId;

    /**
     * 支付主体
     */
    @NotEmpty(message = "支付主体不能为空")
    @Valid
    private List<AcmKeepAccountPayMemberVO> payOwners;

    /**
     * 支付对手
     */
    @NotEmpty(message = "支付对手不能为空")
    @Valid
    private List<AcmKeepAccountPayMemberVO> payOthers;

    public String getPayCoreId() {
        return payCoreId;
    }

    public void setPayCoreId(String payCoreId) {
        this.payCoreId = payCoreId;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
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

    public int getReversal() {
        return reversal;
    }

    public void setReversal(int reversal) {
        this.reversal = reversal;
    }

    public String getReversalTransId() {
        return reversalTransId;
    }

    public void setReversalTransId(String reversalTransId) {
        this.reversalTransId = reversalTransId;
    }

    public List<AcmKeepAccountPayMemberVO> getPayOwners() {
        return payOwners;
    }

    public void setPayOwners(List<AcmKeepAccountPayMemberVO> payOwners) {
        this.payOwners = payOwners;
    }

    public List<AcmKeepAccountPayMemberVO> getPayOthers() {
        return payOthers;
    }

    public void setPayOthers(List<AcmKeepAccountPayMemberVO> payOthers) {
        this.payOthers = payOthers;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
