package com.flouis.base.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

	public final static Long DEFAULT_TIME = 60L;
	public final static TimeUnit DEFAULT_TIMEUNIT = TimeUnit.MINUTES;

	private static RedisUtil redisUtil = null;

	private RedisUtil(){}

	@Getter
	@Setter
	@Resource
	private RedisTemplate redisTemplate;

	@PostConstruct
	private void init(){
		redisUtil = new RedisUtil();
		redisUtil.setRedisTemplate(this.redisTemplate);
	}

	/* 存值 */
	public static void set(String k, String v, Long l, TimeUnit timeUnit){
		redisUtil.getRedisTemplate().opsForValue().set(k, v, l, timeUnit);
	}

	public static void set(String k, String v){
		set(k, v, DEFAULT_TIME, DEFAULT_TIMEUNIT);
	}

	/* 取值 */
	public static Object get(String k){
		return redisUtil.getRedisTemplate().opsForValue().get(k);
	}

	public static String getString(String k){
		return String.valueOf(redisUtil.getRedisTemplate().opsForValue().get(k));
	}

}
