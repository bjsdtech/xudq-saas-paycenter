package com.xudq.api.ons.producer;

import com.aliyun.openservices.ons.api.*;
import com.xudq.api.ons.util.OnsMsgFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description ons生产者
 * @date
 */
public class OnsMsgProducer {
	protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(OnsMsgProducer.class);

	@Autowired
	private Producer producer;

	  @Value("${ons.ProducerId}")
	  private String ONS_PRODUCER_ID;
	  @Value("${ons.AccessKey}")
	  private String ONS_ACCESS_KEY;
	  @Value("${ons.SecretKey}")
	  private String ONS_SECRET_KEY;

	/**
	 * 发送实时消息
	 */
	public String pushMessage(String topic, String tag, String msgStr) throws InterruptedException {
		if (producer == null) {
			logger.info("Ons producer: producer is null, try to reconnect...");
			reconnectONS();
		}

		Message msg = OnsMsgFactory.getInstance(topic, tag, msgStr);
		SendResult sendResult = producer.send(msg);
		String messageId = sendResult.getMessageId();
		if (StringUtils.isNotBlank(messageId)) {
			logger.info("[OnsProducer] : " + "{\"messageId\":\"" + messageId +
					"\",\"topic\":\"" + topic + "\",\"tag\":\"" + tag +
					"\",\"msgStr\":" + msgStr + "}");
		}
		return messageId;
	}

	/**
	 * 重连ONS, 每隔500ms重试一次
	 */
	private synchronized void reconnectONS() throws InterruptedException {
		while (producer == null) {
			logger.info("Ons producer: producer is null, try to reconnect...");
			if (getNewProducer()) {
				break;
			} else {
				Thread.sleep(500);
			}
		}
	}

	/**
	 * 重连ONS的实际代码
	 */
	private synchronized boolean getNewProducer() {
		logger.info("Ons new producer start...");
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.ProducerId, ONS_PRODUCER_ID);
		properties.put(PropertyKeyConst.AccessKey, ONS_ACCESS_KEY);
		properties.put(PropertyKeyConst.SecretKey, ONS_SECRET_KEY);
		producer = ONSFactory.createProducer(properties);

		if (producer != null) {
			producer.start();
			return true;
		}
		return false;
	}

	public String getONS_SECRET_KEY() {
		return ONS_SECRET_KEY;
	}

	public void setONS_SECRET_KEY(String ONS_SECRET_KEY) {
		this.ONS_SECRET_KEY = ONS_SECRET_KEY;
	}

	public String getONS_PRODUCER_ID() {
		return ONS_PRODUCER_ID;
	}

	public void setONS_PRODUCER_ID(String ONS_PRODUCER_ID) {
		this.ONS_PRODUCER_ID = ONS_PRODUCER_ID;
	}

	public String getONS_ACCESS_KEY() {
		return ONS_ACCESS_KEY;
	}

	public void setONS_ACCESS_KEY(String ONS_ACCESS_KEY) {
		this.ONS_ACCESS_KEY = ONS_ACCESS_KEY;
	}
}
