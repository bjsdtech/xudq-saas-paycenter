package com.xudq.base.eunms;
/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public enum ResultInfoVOStatus {
	SUCCESSFUL(0, "Success"),
	FAILURE(-1, "Failure");

	private Integer code;
	private String desc;

	private ResultInfoVOStatus(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static ResultInfoVOStatus fromValue(int value) {
		for (ResultInfoVOStatus item : ResultInfoVOStatus.values()) {
			if (item.getCode() == value) {
				return item;
			}
		}
		return null;
	}


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
