package com.xudq.api.ons.util;


import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public class TimstampUtils {
	
	public static Timestamp getCurrent() {
		Timestamp current = new Timestamp(System.currentTimeMillis()); 
		return current;
	}

	public static String getDateString(Object date) {
		if(date instanceof Timestamp) {
			return ((Timestamp)date).toString();
		}
		return "unknown data type";
	}
	
	public static Date getDate(String strDate) {
		Timestamp ts = Timestamp.valueOf(strDate);
		return ts;
	}

	public static Timestamp getTimestamp(String start_time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = null;
		if(StringUtils.isBlank(start_time))
			return null;
		try {
			timestamp =  new Timestamp(sdf.parse(start_time).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}
}
