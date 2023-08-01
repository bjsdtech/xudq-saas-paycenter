package com.xudq.api.po;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description channel实体，取自paycenter.t_channel
 * @date
 */
public class Channel {
    private int channelId;              //渠道id，自增
    private String accountId = "";      //渠道在PAYMENT内的账户ID
    private String merId = "";          //PAYMENT在渠道的商户号
    private String merName = "";        //PAYMENT在渠道的商户名
    private String channelType = "";    //渠道类型
    private String channelName = "";    //渠道名称
    private String channelDesc = "";    //渠道描述
    private String channelStatus = "";  //渠道状态
    private int passagewayId;
    private String passagewayName;
    private int companyId;
    private String companyName;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
    }

    public String getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(String channelStatus) {
        this.channelStatus = channelStatus;
    }

    public int getPassagewayId() {
        return passagewayId;
    }

    public void setPassagewayId(int passagewayId) {
        this.passagewayId = passagewayId;
    }

    public String getPassagewayName() {
        return passagewayName;
    }

    public void setPassagewayName(String passagewayName) {
        this.passagewayName = passagewayName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
