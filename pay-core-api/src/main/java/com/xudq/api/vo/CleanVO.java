package com.xudq.api.vo;

public class CleanVO {

    private String phone1;
    private String name;
    private String amount;
    private String comUserId;
    private String userId;

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getComUserId() {
        return comUserId;
    }

    public void setComUserId(String comUserId) {
        this.comUserId = comUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CleanVO{" +
                "phone1='" + phone1 + '\'' +
                ", name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", comUserId='" + comUserId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
