package com.xudq.api.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 收款单VO
 * @date
 */
public class RecvableVO {

    private String business_order_id;
    private String order_id;
    private String business_token;
    private String channel_mer_id;
    private String amount;
    private String pay_type;
    private String pay_status;
    private String channel_trans_id;
    private String refer_no;
    private String source;
    private String user_id;
    private String com_user_id;
    private String create_time;
    private String pay_time;
    private String pos_data;

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

    public String getBusiness_token() {
        return business_token;
    }

    public void setBusiness_token(String business_token) {
        this.business_token = business_token;
    }

    public String getChannel_mer_id() {
        return channel_mer_id;
    }

    public void setChannel_mer_id(String channel_mer_id) {
        this.channel_mer_id = channel_mer_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getChannel_trans_id() {
        return channel_trans_id;
    }

    public void setChannel_trans_id(String channel_trans_id) {
        this.channel_trans_id = channel_trans_id;
    }

    public String getRefer_no() {
        return refer_no;
    }

    public void setRefer_no(String refer_no) {
        this.refer_no = refer_no;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCom_user_id() {
        return com_user_id;
    }

    public void setCom_user_id(String com_user_id) {
        this.com_user_id = com_user_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getPos_data() {
        return pos_data;
    }

    public void setPos_data(String pos_data) {
        this.pos_data = pos_data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
