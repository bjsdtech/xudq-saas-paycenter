package com.xudq.api.service;

import com.xudq.api.util.JedisUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.function.Function;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description JedisService
 * @date
 */
@Service
public class JedisService {

	private final static Logger logger = LoggerFactory.getLogger(JedisService.class);

	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private Integer port;
	@Value("${spring.redis.password}")
	private String password;
	
	private final String LOCK_PRE="PAYCORE_LOCK_";

	private final String DUPLICATE_PRE="PAYCORE_DUPLICATE_";
	
	private static JedisPool jedisPool;

	/**
	 * 得到key中存储的内容
	 *
	 * @param key
	 * @return
	 */
	public String getByKey(String key) {
		if (StringUtils.isBlank(key)) {
			logger.error("empty key, key {}", key);
			return null;
		}

		Object s = oper(JedisUtil.get(LOCK_PRE + key));
		return s == null ? null : s.toString();
	}

	/**
	 * 设置redis的key为value，超时为expire
	 *
	 * @param key
	 * @return
	 */
	public boolean setByKey(String key, String value, int expire) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
			logger.error("empty key or value, key {}, value {}", key, value);
			return false;
		}

		String s = oper(JedisUtil.set(LOCK_PRE + key, value, expire)).toString();
		return !(StringUtils.isBlank(s) || !s.equals("OK"));
	}

	/**
	 * 设置redis的key为value，不存在时设置
	 * 前缀 PAYCENTER_LOCK_
	 * @param key
	 * @return
	 */
	public boolean setNxByKey(String key, String value, Integer expire) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value) || expire == null) {
			logger.error("empty key or value, key {}, value {}, expire {}", key, value, expire);
			return false;
		}

		Object s = oper(JedisUtil.setnx(LOCK_PRE + key, value, expire));
		if(s == null || StringUtils.isBlank(s.toString())){
			return false;
		}else if(s.toString().equals("OK")){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 设置redis的key为value，不存在时设置
	 * 前缀 PAYCENTER_DUPLICATE_
	 * @param key
	 * @return
	 */
	public boolean setNxByKeyDuplicate(String key, String value, Integer expire) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value) || expire == null) {
			logger.error("empty key or value, key {}, value {}, expire {}", key, value, expire);
			return false;
		}

		Object s = oper(JedisUtil.setnx(DUPLICATE_PRE + key, value, expire));
		if(s == null || StringUtils.isBlank(s.toString())){
			return false;
		}else if(s.toString().equals("OK")){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 设置超时
	 *
	 * @param key
	 * @return
	 */
	public boolean setExpire(String key, Integer expire) {
		if (StringUtils.isBlank(key) || expire == null) {
			logger.error("empty key or expire, key {}, expire {}", key, expire);
			return false;
		}

		String s = oper(JedisUtil.expire(LOCK_PRE + key, expire)).toString();
		return !(StringUtils.isBlank(s) || !s.equals("1"));
	}


	/**
	 * 增加key的值
	 *
	 * @param key
	 * @return
	 */
	public int incByKey(String key) {
		if (StringUtils.isBlank(key)) {
			logger.error("empty key , key {}", key);
			return -1;
		}

		Object s = oper(JedisUtil.inc(LOCK_PRE + key, 1L));
		return s == null ? -1 : Integer.valueOf(s.toString());
	}

	/**
	 * get redis conection
	 *
	 * @return
	 */
	private static Jedis getRedisResource() {
		if (null == jedisPool) {
			throw new NullPointerException("please connect to redis first!");
		}

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		} catch (JedisConnectionException e) {
			logger.error("redis connection error", e);
			return null;
		}
		return jedis;
	}

	/**
	 * apply f to jedis
	 *
	 * @param f
	 * @return
	 */
	private static Object oper(Function<Jedis, Object> f) {
		if (f == null) return null;
		Jedis jedis = getRedisResource();

		if (jedis == null) {
			logger.error("get resource failed");
			return null;
		}

		try {
			Object o = f.apply(jedis);
			return o;
		} catch (Exception e) {
			logger.error("redis operation failed", e);
			return null;
		} finally {
			jedis.close();
		}
	}

	@PostConstruct
	public void init() {
		logger.info("init jedis, host {}, port {}, password {}", host, port, password);
		connect(host, port, password);
	}

	@PreDestroy
	public void close() {
		logger.info("close jedis, host {}, port {}, password {}", host, port, password);
		if (jedisPool != null && !jedisPool.isClosed()) {
			jedisPool.close();
		}
	}

	/**
	 * connect to redis
	 *
	 * @param host
	 * @param port
	 * @param password
	 */
	private synchronized static void connect(String host, Integer port, String password) {
		if (jedisPool != null && !jedisPool.isClosed()) return;        // 防止重复连接

		if (StringUtils.isEmpty(host) || port == null || port < 0 || port > 65535) {
			throw new IllegalArgumentException("redis connection error, host = " + host + ", port=" + port);
		}

		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(1000);
		jedisPoolConfig.setMaxIdle(100);
		if (StringUtils.isEmpty(password)) {
			jedisPool = new JedisPool(jedisPoolConfig, host, port, 10000);
		} else {
			jedisPool = new JedisPool(new JedisPoolConfig(), host, port, 10000, password);
		}
	}

    /**
     * 添加重载方法
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setByKey(String key, String value) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            logger.error("empty key or value, key {}, value {}", key, value);
            return false;
        }
        String s = oper(JedisUtil.set(LOCK_PRE + key, value)).toString();
        return !(StringUtils.isBlank(s) || !s.equals("OK"));
    }
}
