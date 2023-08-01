package com.xudq.api.vo;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    /**
     * 操作成功
     */
    private static int RES_CODE_SUCC = 0;

    /**
     * 参数错误
     */
    private static int RES_CODE_PARAM_ERR = 1;

    /**
     * 系统错误
     */
    private static int RES_CODE_SYS_ERR = 2;

    private int status;
    private String errMsg;
    private T data;
    private int total = 0;

    public BaseResponse() {
    }

    public BaseResponse(int code, String msg) {
        this.status = code;
        this.errMsg = msg;
    }

    public BaseResponse(int code, String msg, T data) {
        this.status = code;
        this.errMsg = msg;
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return RES_CODE_SUCC == status;
    }


    public static <T> BaseResponse<T> buildSucc(String msg) {
        BaseResponse response = new BaseResponse(RES_CODE_SUCC, msg);
        return response;
    }

    public static <T> BaseResponse<T> buildSucc(String msg, T data) {
        BaseResponse response = new BaseResponse(RES_CODE_SUCC, msg, data);
        return response;
    }

    public static <T> BaseResponse<T> buildSucc(T data) {
        BaseResponse response = new BaseResponse(RES_CODE_SUCC, "成功", data);
        return response;
    }

    public static <T> BaseResponse<T> buildSucc(T data, int total) {
        BaseResponse response = new BaseResponse(RES_CODE_SUCC, "成功", data);
        response.setTotal(total);
        return response;
    }

    public static <T> BaseResponse<T> buildParamFail(String msg) {
        BaseResponse response = new BaseResponse(RES_CODE_PARAM_ERR, msg);
        return response;
    }

    public static <T> BaseResponse<T> buildSysFail(String msg) {
        BaseResponse response = new BaseResponse(RES_CODE_SYS_ERR, msg);
        return response;
    }
}
