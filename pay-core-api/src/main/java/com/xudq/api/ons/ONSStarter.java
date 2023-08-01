package com.xudq.api.ons;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description JobStarter
 * @date
 */
@Service
public class ONSStarter implements InitializingBean {

	@Resource
	private ONSSendRevTest onsSendRevTest;

	@Override
	public void afterPropertiesSet() throws Exception {
		/*for (int i = 0; i < 10; i++) {
			onsSendRevTest.send();
			Thread.sleep(700);
		}*/
	}
}
