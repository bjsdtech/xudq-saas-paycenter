package com.xudq.api.util;

import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.function.Function;


public class JedisUtil {

	/**
	 * 根据主键获取
	 *
	 * @param key
	 * @return
	 */
	public static Function<Jedis, Object> get(String key) {
		if (StringUtils.isEmpty(key))
			throw new NullPointerException("key cannot be null");

		return (Jedis jedis) -> jedis.get(key);
	}

	/**
	 * 根据主键设置, 不过期
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public static Function<Jedis, Object> set(String key, String value) {
		return set(key, value, null);
	}

	/**
	 * 根据主键设置, expire单位为s
	 *
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public static Function<Jedis, Object> set(String key, String value, Integer expire) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value))
			throw new NullPointerException("key or value cannot be null");

		if (expire == null) {
			return (Jedis jedis) -> jedis.set(key, value);
		} else {
			return (Jedis jedis) -> jedis.setex(key, expire, value);
		}
	}

	/**
	 * 根据主键增加Value的值
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public static Function<Jedis, Object> inc(String key, Long value) {
		if (StringUtils.isEmpty(key) || value == null)
			throw new NullPointerException("key or value cannot be null");

		return (Jedis jedis) -> jedis.incrBy(key, value);
	}

	/**
	 * 根据主键设置；不存在时设置
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	@Deprecated
	public static Function<Jedis, Object> setnx(String key, String value) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value))
			throw new NullPointerException("key or value cannot be null");

		return (Jedis jedis) -> jedis.setnx(key, value);
	}


	/**
	 * 根据主键设置；不存在时设置，支持超时
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public static Function<Jedis, Object> setnx(String key, String value, Integer expire) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value) || expire == null)
			throw new NullPointerException("key or value or expire cannot be null");

		return (Jedis jedis) -> jedis.set(key, value, "nx", "ex", expire);
	}
	/**
	 * 设置键的超时时间
	 *
	 * @param key
	 * @param expire
	 * @return
	 */
	public static Function<Jedis, Object> expire(String key, Integer expire) {
		if (StringUtils.isEmpty(key) || expire == null)
			throw new NullPointerException("key or time cannot be null");
		return (Jedis jedis) -> jedis.expire(key, expire);
	}
}
