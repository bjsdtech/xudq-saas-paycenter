package com.xudq.api.po;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 渠道信息的map，根据mer_id获取channel
 * @date
 */
@Component
public class ChannelMap {

    public ChannelMap(){
        //TODO init channels,最好使用cache，五分钟失效
        getChannelMap();
    }

    private static List<Channel> channels = Lists.newArrayList();
    public static Map<String, Channel> channelsMap = Maps.newHashMap();

    public static Map<String, Channel> getChannelMap(){
        // map为空，使用init方法
        if(channelsMap == null || channelsMap.size() <= 0){
            init();
        }
        channelsMap = channels.stream().collect(Collectors.toMap(Channel -> Channel.getMerId(), Channel -> Channel));
        return channelsMap;
    }

    /**
     * init 方法，根据mer_id获取channel，channel的信息来自paycenter.t_channel,通过接口获取缓存数据
     * */
    public static void init(){
        // TODO 通过Http接口获取Channels
        //TODO 测试暂时写死

        Channel channel1 = new Channel();
        // TODO 这个是认证支付的merId
        channel1.setMerId("202202111001936526");
        channel1.setAccountId("10011000");
        channel1.setChannelId(1000);
        Channel channel2 = new Channel();
        channel2.setMerId("202202111001138505");
        channel2.setAccountId("10011001");
        channel2.setChannelId(1001);
        Channel channel3 = new Channel();
        channel3.setMerId("200001340139");
        channel3.setAccountId("10021002");
        channel3.setChannelId(1002);
        Channel channel4 = new Channel();
        channel4.setMerId("202202111001426700");
        channel4.setAccountId("10011005");
        channel4.setChannelId(1005);
        Channel channel5 = new Channel();
        channel5.setMerId("200001340142");
        channel5.setAccountId("10021006");
        channel5.setChannelId(1006);
        channels.add(channel1);
        channels.add(channel2);
        channels.add(channel3);
        channels.add(channel4);
        channels.add(channel5);
    }

    /**
     * 根据mer_id获取channel
     * */
    public static Channel getChannel(String channelMerId){
        // map为空，使用init方法
        if(channelsMap == null || channelsMap.size() <= 0){
            getChannelMap();
        }
        return channelsMap.get(channelMerId);
    }
}
