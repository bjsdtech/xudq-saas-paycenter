package com.xudq.api.ons.util;

import com.aliyun.openservices.ons.api.Message;
import org.springframework.util.StringUtils;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description Ons消息工厂
 * Message Topic, 消息的全局主题
 * Message Tag, 消息标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤
 * Message Body, 任何二进制形式的数据, 收发双方规定好序列化/反序列化方式即可
 * @date
 */
public class OnsMsgFactory {

	public static Message getInstance() {
		return new Message();
	}

	public static Message getInstance(String topic, String tag, String body) {
		if (StringUtils.isEmpty(body)) {
			body = "";
		}

		Message msg = new Message(topic, tag, body.getBytes());
		return msg;
	}
}
