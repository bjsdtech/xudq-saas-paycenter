package com.xudq.api.exceptions;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public class BusinessException extends RuntimeException {

    private String msg;

    public BusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
