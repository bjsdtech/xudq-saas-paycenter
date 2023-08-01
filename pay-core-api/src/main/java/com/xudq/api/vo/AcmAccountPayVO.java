package com.xudq.api.vo;

import java.io.Serializable;
import java.util.List;

public class AcmAccountPayVO implements Serializable {

    /**
     * 支付核心的支付id
     */
    private String payCoreId;

    /**
     * 支付交易的各账户流水
     */
    private List<AcmAccountPayDetail> details;

    public String getPayCoreId() {
        return payCoreId;
    }

    public void setPayCoreId(String payCoreId) {
        this.payCoreId = payCoreId;
    }

    public List<AcmAccountPayDetail> getDetails() {
        return details;
    }

    public void setDetails(List<AcmAccountPayDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "AcmAccountPayVO{" +
                "payCoreId=" + payCoreId +
                ", details=" + details +
                '}';
    }
}
