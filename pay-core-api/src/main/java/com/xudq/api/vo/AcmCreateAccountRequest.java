package com.xudq.api.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AcmCreateAccountRequest extends BaseRequest {

    /**
     * 公司用户中心用户ID
     */
    //@NotNull(message = "用户ID不能为空")
    private String comUserId;

    /**
     * 用户登录密码
     */
    private String loginPassword;

    /**
     * 用户支付密码
     */
    private String payPassword;

    /**
     * 手机号
     */
    @Pattern(regexp = "1[\\d]{10}", message = "手机号格式有误")
    private String buyerPhone;

    /**
     * 邮箱
     */
    @Pattern(regexp = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+", message = "邮箱格式有误")
    private String email;

    /**
     * 用户地址
     */
    private String userAddr;

    /**
     * 最后登录ip
     */
    private String lastLoginIp;

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getComUserId() {
        return comUserId;
    }

    public void setComUserId(String comUserId) {
        this.comUserId = comUserId;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    @Override
    public String toString() {
        return "AcmCreateAccountRequest{" +
                "comUserId='" + comUserId + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", payPassword='" + payPassword + '\'' +
                ", email='" + email + '\'' +
                ", userAddr='" + userAddr + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                "} " + super.toString();
    }
}
