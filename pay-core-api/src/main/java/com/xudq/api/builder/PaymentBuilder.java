package com.xudq.api.builder;

import com.xudq.api.po.Payment;

import java.util.Date;
import java.lang.Short;
import java.lang.Integer;
import java.lang.String;
import java.lang.Long;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public class PaymentBuilder {
    public static class Builder {
        private final Payment payment = new Payment();

        public Builder id(Integer id) {
            payment.setId(id);
            return this;
        }

        public Builder paymentId(String paymentId) {
            payment.setPaymentId(paymentId);
            return this;
        }

        public Builder frozenId(String frozenId) {
            payment.setFrozenId(frozenId);
            return this;
        }

        public Builder approveId(String approveId) {
            payment.setApproveId(approveId);
            return this;
        }

        public Builder collectId(String collectId) {
            payment.setCollectId(collectId);
            return this;
        }

        public Builder paymentType(Integer paymentType) {
            payment.setPaymentType(paymentType);
            return this;
        }

        public Builder paymentStatus(Integer paymentStatus) {
            payment.setPaymentStatus(paymentStatus);
            return this;
        }

        public Builder busiId(String busiId) {
            payment.setBusiId(busiId);
            return this;
        }

        public Builder paymentFrom(String paymentFrom) {
            payment.setPaymentFrom(paymentFrom);
            return this;
        }

        public Builder serviceType(Integer serviceType) {
            payment.setServiceType(serviceType);
            return this;
        }

        public Builder paymentMode(Integer paymentMode) {
            payment.setPaymentMode(paymentMode);
            return this;
        }

        public Builder curencyType(String curencyType) {
            payment.setCurencyType(curencyType);
            return this;
        }

        public Builder amount(Integer amount) {
            payment.setAmount(amount);
            return this;
        }

        public Builder orderAmount(Integer orderAmount) {
            payment.setOrderAmount(orderAmount);
            return this;
        }

        public Builder sendChannelTransId(String sendChannelTransId) {
            payment.setSendChannelTransId(sendChannelTransId);
            return this;
        }

        public Builder channelReturnTransId(String channelReturnTransId) {
            payment.setChannelReturnTransId(channelReturnTransId);
            return this;
        }

        public Builder comUserId(String comUserId) {
            payment.setComUserId(comUserId);
            return this;
        }

        public Builder toAccountId(Long toAccountId) {
            payment.setToAccountId(toAccountId);
            return this;
        }

        public Builder toAccountType(Short toAccountType) {
            payment.setToAccountType(toAccountType);
            return this;
        }

        public Builder toIdType(Short toIdType) {
            payment.setToIdType(toIdType);
            return this;
        }

        public Builder toUserName(String toUserName) {
            payment.setToUserName(toUserName);
            return this;
        }

        public Builder toIdNo(String toIdNo) {
            payment.setToIdNo(toIdNo);
            return this;
        }

        public Builder toPhone(String toPhone) {
            payment.setToPhone(toPhone);
            return this;
        }

        public Builder toBankCardNum(String toBankCardNum) {
            payment.setToBankCardNum(toBankCardNum);
            return this;
        }

        public Builder toBankName(String toBankName) {
            payment.setToBankName(toBankName);
            return this;
        }

        public Builder toOpenAccountBank(String toOpenAccountBank) {
            payment.setToOpenAccountBank(toOpenAccountBank);
            return this;
        }

        public Builder toAreaName(String toAreaName) {
            payment.setToAreaName(toAreaName);
            return this;
        }

        public Builder toCityName(String toCityName) {
            payment.setToCityName(toCityName);
            return this;
        }

        public Builder channelId(Integer channelId) {
            payment.setChannelId(channelId);
            return this;
        }

        public Builder channelName(String channelName) {
            payment.setChannelName(channelName);
            return this;
        }

        public Builder fromAccountId(Long fromAccountId) {
            payment.setFromAccountId(fromAccountId);
            return this;
        }

        public Builder fromAccountType(Short fromAccountType) {
            payment.setFromAccountType(fromAccountType);
            return this;
        }

        public Builder transCode(String transCode) {
            payment.setTransCode(transCode);
            return this;
        }

        public Builder payTime(Date payTime) {
            payment.setPayTime(payTime);
            return this;
        }

        public Builder extPayTime(Date extPayTime) {
            payment.setExtPayTime(extPayTime);
            return this;
        }

        public Builder sourceIp(String sourceIp) {
            payment.setSourceIp(sourceIp);
            return this;
        }

        public Builder userMac(String userMac) {
            payment.setUserMac(userMac);
            return this;
        }

        public Builder failReason(String failReason) {
            payment.setFailReason(failReason);
            return this;
        }

        public Builder comments(String comments) {
            payment.setComments(comments);
            return this;
        }

        public Builder isDeleted(Integer isDeleted) {
            payment.setIsDeleted(isDeleted);
            return this;
        }

        public Builder lastModifyTime(Date lastModifyTime) {
            payment.setLastModifyTime(lastModifyTime);
            return this;
        }

        public Builder createTime(Date createTime) {
            payment.setCreateTime(createTime);
            return this;
        }

        public Builder updateTime(Date updateTime) {
            payment.setUpdateTime(updateTime);
            return this;
        }

        public Builder reserveInt1(Integer reserveInt1) {
            payment.setReserveInt1(reserveInt1);
            return this;
        }

        public Builder reserveChar1(String reserveChar1) {
            payment.setReserveChar1(reserveChar1);
            return this;
        }

        public Payment build() {
            return payment;
        }
    }
}

