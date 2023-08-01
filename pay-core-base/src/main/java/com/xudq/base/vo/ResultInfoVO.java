package com.xudq.base.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xudq.base.eunms.ResultInfoVOStatus;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public class ResultInfoVO {

	private int status;

	@JsonProperty(value = "err_msg")
	private String errMsg;

	private Object data;


	public ResultInfoVO() {
		this.status = ResultInfoVOStatus.FAILURE.getCode();
		this.errMsg = ResultInfoVOStatus.FAILURE.getDesc();
	}

	public ResultInfoVO(ResultInfoVOStatus resultStatus, Object data) {
		this.status = resultStatus.getCode();
		this.errMsg = resultStatus.getDesc();
		this.data = data;
	}

	public ResultInfoVO(Integer status , String errMsg) {
		this.status = status;
		this.errMsg = errMsg;
	}

	public ResultInfoVO(Integer status , String errMsg, Object data) {
		this.status = status;
		this.errMsg = errMsg;
		this.data = data;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}