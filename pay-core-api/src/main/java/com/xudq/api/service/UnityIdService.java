package com.xudq.api.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 生成收款单，收款单的具体规则待定
 * @date
 */
@Service
public class UnityIdService {
	
	private static final Logger logger = LoggerFactory.getLogger(UnityIdService.class);
	
	//机器序号，用来生成唯一订单id前两位
	private String machineId;

	/**
	 * 获取机器码最后两位，防止不同机器的单子撞起来
	 * */
	@PostConstruct
	private void initMethod(){
		try{
			InetAddress localHost = InetAddress.getLocalHost();
			String name = localHost.getHostName();
			logger.info("machineId init, hostName : " + name);
			this.machineId = name.substring(name.length()-2);
			logger.info("machineId init finish: " + this.machineId);
		}catch(Exception e){
			logger.error("machineId init error", e);
			throw new RuntimeException();
		}
	}
	
	/**
	 * 生成全局唯一的收款单
	 * TODO 生成规则需要修改
	 * */
	public String getUnityId(String source, String orderId){
		source = String.format("%05d", Integer.parseInt(source));
		orderId = StringUtils.isBlank(orderId)?"":orderId.trim();
		int orderIdLen = orderId.length();
		StringBuilder sb = new StringBuilder("");
		if(orderIdLen >= 12){
			orderId = orderId.substring(orderIdLen - 12);
		}
		while(orderIdLen < 12){
			sb.append("0");
			orderIdLen++;
		}
		orderId = sb.append(orderId).toString();
		//TODO 加上一些随机数，uuid的截取端或者自增数字等
		return new StringBuilder(getTimeStr()).append(source).append(orderId).append(machineId)
		.toString();
	}

	public String getTimeStr() {
		Timestamp current = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(current);
	}
}
