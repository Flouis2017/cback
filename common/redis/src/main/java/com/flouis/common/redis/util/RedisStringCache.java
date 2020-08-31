package com.flouis.common.redis.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class RedisStringCache {

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	private int captchaExpireTime;
	private int accountExpireTime;
	private int orderExpireTime;

	public RedisStringCache(int captchaExpireTime, int accountExpireTime, int orderExpireTime){
		this.setAccountExpireTime(accountExpireTime);
		this.setCaptchaExpireTime(captchaExpireTime);
		this.setOrderExpireTime(orderExpireTime);
	}

	// 设值
	public void set(String key, String value, CacheType cacheType){
		int expireTime;
		switch (cacheType){
			case ACCOUNT:
				expireTime = this.getAccountExpireTime();
				break;
			case CAPTCHA:
				expireTime = this.getCaptchaExpireTime();
				break;
			case ORDER:
				expireTime = this.getOrderExpireTime();
				break;
			case TRADE:
			case POSI:
			default:
				expireTime = 10;
		}
		this.getStringRedisTemplate().opsForValue().set(cacheType.getType() + key, value, expireTime, TimeUnit.SECONDS);
	}

	// 查询
	public String get(String key, CacheType cacheType){
		return this.getStringRedisTemplate().opsForValue().get(cacheType.getType() + key);
	}

	// 删除
	public void remove(String key, CacheType cacheType){
		this.getStringRedisTemplate().delete(cacheType.getType() + key);
	}

}
