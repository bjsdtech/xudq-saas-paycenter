package com.xudq.api.po;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public class FlowSplit implements Cloneable{

    private int id;
    private String business_order_id = "";
    private String order_id = "";
    private int foreign_key;
    private String fee_type = "";
    private int amount;	//单位为分
    private int cash_flow; //流水方向，0收款，1退款

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusiness_order_id() {
		return business_order_id;
	}

	public void setBusiness_order_id(String business_order_id) {
		this.business_order_id = business_order_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public int getForeign_key() {
		return foreign_key;
	}

	public void setForeign_key(int foreign_key) {
		this.foreign_key = foreign_key;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCash_flow() {
		return cash_flow;
	}

	public void setCash_flow(int cash_flow) {
		this.cash_flow = cash_flow;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
