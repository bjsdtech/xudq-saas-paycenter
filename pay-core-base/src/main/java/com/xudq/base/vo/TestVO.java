package com.xudq.base.vo;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public class TestVO {

    @NotNull(message = "渠道不能为空")
    @Digits(integer = 11, fraction = 0, message = "渠道为整数")
    @Range(min = 0, max = 20, message = "渠道范围不正确")
    private Integer channel;

    @Digits(integer = 11, fraction = 0, message = "dp/sp为整数")
    @Range(min = 1, max = Integer.MAX_VALUE, message = "dp/sp范围不正确")
    private Integer partnerId;

    /**
     * 跟进bd人员id
     */
    @Digits(integer = 11, fraction = 0, message = "followId为整数")
    @Range(min = 1, max = Integer.MAX_VALUE, message = "followId范围不正确")
    private Integer followerId;

    /**
     * 跟进bd人员 姓名
     */
    @Pattern(regexp = "[\\u4e00-\\u9fa5_a-zA-Z0-9]{0,20}", message = "bd人员姓名为中文字符，大小写英文字母，数字或者下划线")
    private String followerName;

    @Digits(integer = 1, fraction = 0, message = "是否来源大宗订单为整数")
    @Range(min = 0, max = 1, message = "是否来源大宗订单范围不正确")
    private Integer blockOrderSource; // 0-是 1-否

    private String blockOrderNumber;

    private String driverName;

    @NotBlank(message = "个人手机号码")
    private String driverPhone;

    @Pattern(regexp = "^(\\d{18,18}|\\d{15,15}|(\\d{17,17}[X]))$", message = "身份证号格式错误")
    private String driverIdNumber;

    @Pattern(regexp = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", message = "初领驾驶证日期格式错误")
    private String driverLicenseDate;

    private String driverDDAccount;

    @NotBlank(message = "省不能为空")
    private String province;

    @NotBlank(message = "城市不能为空")
    private String city;

    @Digits(integer = 20, fraction = 0, message = "品牌id为整数")
    @Range(min = 0, max = Integer.MAX_VALUE, message = "品牌id范围不正确")
    private Integer brandId;

    private String brandName;

    @Digits(integer = 20, fraction = 0, message = "id为整数")
    @Range(min = 0, max = Integer.MAX_VALUE, message = "id范围不正确")
    private Integer seriesId;

    private String seriesName;

    @Digits(integer = 20, fraction = 0, message = "id为整数")
    @Range(min = 0, max = Integer.MAX_VALUE, message = "id范围不正确")
    private Integer modelId;

    private String modelName;

    @Pattern(regexp = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$", message = "预计到店时间格式错误")
    private String estimateArrivalTime;

    @Digits(integer = 20, fraction = 0, message = "方案首付金额为整数")
    @Range(min = 0, message = "方案首付金额范围不正确")
    private BigDecimal planDownpaymentMoney;

    @Digits(integer = 20, fraction = 0, message = "方案月供金额为整数")
    @Range(min = 0, message = "方案月供金额范围不正确")
    private BigDecimal planMonthPaymentMoney;

    private String remark;
    /**
     * 金融方案(业务类型) 1:直租－轻松购 2:回租－乐享购
     */
    @NotNull(message = "业务类型不能为空")
    @Pattern(regexp = "^[12]$", message = "业务类型值错误")
    private String financePlan;
    /**
     * 银行卡号
     */
    @Pattern(regexp = "^(\\d{16,19})$", message = "银行卡号格式错误")
    private String bankNumber;

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Integer getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }

    public String getFollowerName() {
        return followerName;
    }

    public void setFollowerName(String followerName) {
        this.followerName = followerName;
    }

    public Integer getBlockOrderSource() {
        return blockOrderSource;
    }

    public void setBlockOrderSource(Integer blockOrderSource) {
        this.blockOrderSource = blockOrderSource;
    }

    public String getBlockOrderNumber() {
        return blockOrderNumber;
    }

    public void setBlockOrderNumber(String blockOrderNumber) {
        this.blockOrderNumber = blockOrderNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverIdNumber() {
        return driverIdNumber;
    }

    public void setDriverIdNumber(String driverIdNumber) {
        this.driverIdNumber = driverIdNumber;
    }

    public String getDriverLicenseDate() {
        return driverLicenseDate;
    }

    public void setDriverLicenseDate(String driverLicenseDate) {
        this.driverLicenseDate = driverLicenseDate;
    }

    public String getDriverDDAccount() {
        return driverDDAccount;
    }

    public void setDriverDDAccount(String driverDDAccount) {
        this.driverDDAccount = driverDDAccount;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getEstimateArrivalTime() {
        return estimateArrivalTime;
    }

    public void setEstimateArrivalTime(String estimateArrivalTime) {
        this.estimateArrivalTime = estimateArrivalTime;
    }

    public BigDecimal getPlanDownpaymentMoney() {
        return planDownpaymentMoney;
    }

    public void setPlanDownpaymentMoney(BigDecimal planDownpaymentMoney) {
        this.planDownpaymentMoney = planDownpaymentMoney;
    }

    public BigDecimal getPlanMonthPaymentMoney() {
        return planMonthPaymentMoney;
    }

    public void setPlanMonthPaymentMoney(BigDecimal planMonthPaymentMoney) {
        this.planMonthPaymentMoney = planMonthPaymentMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFinancePlan() {
        return financePlan;
    }

    public void setFinancePlan(String financePlan) {
        this.financePlan = financePlan;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    @Override
    public String toString() {
        return "TestVO{" +
                "channel=" + channel +
                ", partnerId=" + partnerId +
                ", followerId=" + followerId +
                ", followerName='" + followerName + '\'' +
                ", blockOrderSource=" + blockOrderSource +
                ", blockOrderNumber='" + blockOrderNumber + '\'' +
                ", driverName='" + driverName + '\'' +
                ", driverPhone='" + driverPhone + '\'' +
                ", driverIdNumber='" + driverIdNumber + '\'' +
                ", driverLicenseDate='" + driverLicenseDate + '\'' +
                ", driverDDAccount='" + driverDDAccount + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", seriesId=" + seriesId +
                ", seriesName='" + seriesName + '\'' +
                ", modelId=" + modelId +
                ", modelName='" + modelName + '\'' +
                ", estimateArrivalTime='" + estimateArrivalTime + '\'' +
                ", planDownpaymentMoney=" + planDownpaymentMoney +
                ", planMonthPaymentMoney=" + planMonthPaymentMoney +
                ", remark='" + remark + '\'' +
                ", financePlan='" + financePlan + '\'' +
                ", bankNumber='" + bankNumber + '\'' +
                '}';
    }
}
