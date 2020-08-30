package com.flouis.counter.service;

import com.flouis.common.redis.util.CacheType;
import com.flouis.common.redis.util.RedisStringCache;
import com.flouis.common.usual.entity.Captcha;
import com.flouis.common.usual.entity.JsonResult;
import com.flouis.common.usual.util.SnowflakeIdWorker;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Service
public class LoginService {

	@Resource
	private SnowflakeIdWorker idWorker;

	@Resource
	private RedisStringCache redisStringCache;

	public JsonResult captcha() throws IOException {
		// 生成验证码 —— 120*40 4个字符
		Captcha captcha = new Captcha(120, 40, 4, 10);

		// 将验证码<id, 验证码>放入redis缓存中
		String uuid = String.valueOf(this.idWorker.nextId());
		this.redisStringCache.set(uuid, captcha.getCode(), CacheType.CAPTCHA);

		// 使用Base64编码图片，并返回前台
		Map captchaMap = Maps.newHashMap();
		captchaMap.put("id", uuid);
		captchaMap.put("imgBase64", captcha.getBase64ByteStr());

		return JsonResult.success(captchaMap);
	}
}
