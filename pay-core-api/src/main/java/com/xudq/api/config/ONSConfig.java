package com.xudq.api.config;

import com.aliyun.openservices.ons.api.*;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.xudq.api.ons.consumer.OnsMsgConsumer;
import com.xudq.api.ons.producer.OnsMsgProducer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
@Configuration
public class ONSConfig {
	@Value("${ons.ProducerId}")
	private String ProducerId;
	@Value("${ons.ConsumerId}")
	private String ConsumerId;
	@Value("${ons.AccessKey}")
	private String AccessKey;
	@Value("${ons.SecretKey}")
	private String SecretKey;
	@Value("${ons.Topic}")
	private String Topic;
	  
	/**
	 * ons队列生产者
	 */
	@Bean(name="msgToONS")
	public OnsMsgProducer onsMsgProducer() {
		OnsMsgProducer onsMsgProducer = new OnsMsgProducer();
		return onsMsgProducer;
	}

	/**
	 * ons队列消费者
	 */
	@Bean(name="msgFromONS")
	public OnsMsgConsumer onsMsgConsumer() {
		OnsMsgConsumer onsMsgConsumer = new OnsMsgConsumer();
		return onsMsgConsumer;
	}

	/**
	 * ons生产者, 由ons客户端提供, 供msgToONS调用
	 */
	@Bean(name="producer")
	public Producer producer() {
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.ProducerId, ProducerId);
		properties.put(PropertyKeyConst.AccessKey, AccessKey);
		properties.put(PropertyKeyConst.SecretKey, SecretKey);
		Producer producer = ONSFactory.createProducer(properties);

		if (producer != null) {
			producer.start();
		}
		return producer;
	}

	/**
	 * ons消费者, 由ons客户端提供, 供msgFromONS调用
	 */
	@Bean(name="consumer")
	public ConsumerBean consumer(@Qualifier("msgFromONS") OnsMsgConsumer onsMsgConsumer) {
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.ConsumerId, ConsumerId);
		properties.put(PropertyKeyConst.AccessKey, AccessKey);
		properties.put(PropertyKeyConst.SecretKey, SecretKey);
		ConsumerBean consumer = new ConsumerBean();
		consumer.setProperties(properties);

		// 配置消息监听对象
		Map<Subscription, MessageListener> map = new HashMap<Subscription, MessageListener>();
		Subscription subscription = new Subscription();
		subscription.setTopic(Topic);
		subscription.setExpression("*");
		map.put(subscription, onsMsgConsumer);
		consumer.setSubscriptionTable(map);

		if (consumer != null) {
			consumer.start();
		}
		return consumer;
	}
}
