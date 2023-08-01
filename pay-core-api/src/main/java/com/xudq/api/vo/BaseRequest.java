package com.xudq.api.vo;

import java.io.Serializable;

public class BaseRequest implements Serializable {

    private String reqeustId;

    public String getReqeustId() {
        return reqeustId;
    }

    public void setReqeustId(String reqeustId) {
        this.reqeustId = reqeustId;
    }

}
