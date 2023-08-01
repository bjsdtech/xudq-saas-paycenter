package com.xudq.base.common;

import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xudq
 * @version v1.0
 * @Title CurrentTimeHelper.java
 * @Description 返回当前时间，计算两个时间的差值
 * @date
 */
public class TimeHelper {

	/**
	 * 返回当前时间和另一个时间的差值，单位为s
	 */
	public static int timeCompare(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return 0;
		}

		try {
			return (int) ((date2.getTime() - date1.getTime()) / 1e3);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 返回当前时间
	 */
	public static String getTimeNow() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		return time;
	}

	/**
	 * 返回当前日期
	 */
	public static String getDateNow() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String time = df.format(new Date());
		return time;
	}

	/**
	 * 返回时间对应的字符串
	 */
	public static String getTimeStr(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(date);
		return time;
	}

	/**
	 * 返回当前时间对应的字符串，全部由数字组成
	 *
	 * @return
	 */
	public static String getTimeNumberStr() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = df.format(new Date());
		return time;
	}

	/**
	 * 生成年月日
	 *
	 * @return
	 */
	public static int[] getYMD() {
		Calendar now = Calendar.getInstance();

		int year = now.get(Calendar.YEAR) - 2000;
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		return new int[]{year, month, day};
	}

	/**
	 * 计算今天剩下的时间（单位s）
	 *
	 * @return
	 */
	public static int getSecondsLeftToday() {
		Calendar c = Calendar.getInstance();
		Date tomorrow = DateUtils.addDays(c.getTime(), 1);
		tomorrow.setHours(0);
		tomorrow.setMinutes(0);
		tomorrow.setSeconds(0);

		return timeCompare(new Date(), tomorrow);
	}

	/**
	 * 获取diffTime之前的时间
	 * @param date
	 * @param diffTime 秒
     * @return
     */
	public static String getHistoryTime(Date date,long diffTime){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(Long.valueOf(date.getTime() - diffTime * 1000));
		return time;
	}

	public static Date getDateFromStr(String str){
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date time = df.parse(str);
			return time;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static String getYMD( Integer date){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE,date);
		Date time=cal.getTime();
		String YMD = new SimpleDateFormat("yyyy-MM-dd").format(time);
		return YMD;
	}

	public static void main(String[] args) {
//		System.out.println(getDateNow());
//		System.out.println("2016".compareTo("2015"));
		System.out.println(getDateFromStr("2023-01-10 12:12:12.0"));
//		System.out.println(getHistoryTime(new Date(),7776000));
	}
}