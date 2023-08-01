package com.xudq.api.ons;

import com.xudq.api.ons.producer.OnsMsgProducer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description ONSSendRevTest
 * @date
 */
@Service
public class ONSSendRevTest {

	@Resource(name = "msgToONS")
	private OnsMsgProducer onsMsgProducer;

	public void send() throws InterruptedException {
		String json = "{" +
				"\"business_order_id\": \"1Z151661645665000001000032765\"," +
				"\"order_id\": \"1000032765\"," +
				"\"business_token\": \"68C52EABF4F3E60B\"," +
				"\"channel_mer_id\": \"202202101001138505\"," +
				"\"amount\": \"1000\"," +
				"\"pay_status\": \"SUCCESS\"," +
				"\"pay_type\": \"TRANSFER_ACCOUNT\"," +
				"\"channel_trans_id\": \"242787024554\"," +
				"\"refer_no\": \"242923019191\"," +
				"\"source\": \"10012\"," +
				"\"user_id\": \"3231\"," +
				"\"create_time\": \"2022-02-10 18:17:12\"," +
				"\"pay_time\": \"2022-02-10 18:20:00\"," +
				"\"deposit\": \"1000\"," +
				"\"com_user_id\": \"5000010675\"" +
				"}";

		String messageId = onsMsgProducer.pushMessage("FLOW_TO_PC_TEST", "pay-center-pre-order", json);
		System.out.println(messageId);

		messageId = onsMsgProducer.pushMessage("FLOW_TO_PC_TEST", "pay-center-notify", json);
		System.out.println(messageId);
	}
}
