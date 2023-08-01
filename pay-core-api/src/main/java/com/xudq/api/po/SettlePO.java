package com.xudq.api.po;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SettlePO{

    private int id;
    private String order_id="";
    private String sn = "";
	private String business_order_id = "";
    private String source = "";
    private String related_order;
    private String user_id  = ""; 		//刷卡人id
    private String cash_flow = "";
    private String amount = "0";
    private String remark = "";
    private String operate = "";
    private String req_timestamp;
    private int reconsume_times = 0;	//消息的重试次数
	private String channel_mer_id = ""; //渠道商户号
    private String create_time;         //订单创建时间
	private String update_time;
	private String pay_time;
	private String com_user_id; 		//用户中心的id
	private String pay_status; 			//支付状态，SUCCESS||CORRECT
	private String pos_data;			//settle_history中 的ONS消息体，全部保存下来，以防恢复数据，定位问题
	private String c2b_deposit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCom_user_id() {
		return com_user_id;
	}

	public void setCom_user_id(String com_user_id) {
		this.com_user_id = com_user_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getRelated_order() {
		return related_order;
	}

	public void setRelated_order(String related_order) {
		this.related_order = related_order;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCash_flow() {
		return cash_flow;
	}

	public void setCash_flow(String cash_flow) {
		this.cash_flow = cash_flow;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getReq_timestamp() {
		return req_timestamp;
	}

	public void setReq_timestamp(String req_timestamp) {
		this.req_timestamp = req_timestamp;
	}

	public int getReconsume_times() {
		return reconsume_times;
	}

	public void setReconsume_times(int reconsume_times) {
		this.reconsume_times = reconsume_times;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getPay_time() {
		return pay_time;
	}

	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}

	public String getPay_status() {
		return pay_status;
	}

	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}

	public String getPos_data() {
		return pos_data;
	}

	public void setPos_data(String pos_data) {
		this.pos_data = pos_data;
	}

	public String getBusiness_order_id() {
		return business_order_id;
	}

	public void setBusiness_order_id(String business_order_id) {
		this.business_order_id = business_order_id;
	}

	public String getChannel_mer_id() {
		return channel_mer_id;
	}

	public void setChannel_mer_id(String channel_mer_id) {
		this.channel_mer_id = channel_mer_id;
	}

	public String getC2b_deposit() {
		return c2b_deposit;
	}

	public void setC2b_deposit(String c2b_deposit) {
		this.c2b_deposit = c2b_deposit;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
