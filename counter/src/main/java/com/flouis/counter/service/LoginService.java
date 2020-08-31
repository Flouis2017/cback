package com.flouis.counter.service;

import com.flouis.common.redis.util.CacheType;
import com.flouis.common.redis.util.RedisStringCache;
import com.flouis.common.usual.entity.Captcha;
import com.flouis.common.usual.entity.JsonResult;
import com.flouis.common.usual.entity.ResultCode;
import com.flouis.common.usual.util.JsonUtil;
import com.flouis.common.usual.util.SnowflakeIdWorker;
import com.flouis.counter.dao.UserMapper;
import com.flouis.counter.entity.User;
import com.flouis.counter.exception.BusinessException;
import com.flouis.counter.vo.Account;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
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

	@Resource
	private UserMapper userMapper;

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

	/* 登录业务逻辑 */
	public Account login(String uid, String password, String captcha, String captchaId) throws BusinessException {

		// 入参合法校验
		if (StringUtils.isAnyBlank(password, captcha, captchaId) || uid == null){
			throw new BusinessException(ResultCode.LOGIN_INFO_BLANK);
		}

		// 校验验证码
		String captchaFromRedis = this.redisStringCache.get(captchaId, CacheType.CAPTCHA);
		if (StringUtils.isEmpty(captchaFromRedis)){
			throw new BusinessException(ResultCode.CODE_EXPIRED);
		}
		if (!captcha.equalsIgnoreCase(captchaFromRedis)){
			throw new BusinessException(ResultCode.CODE_ERROR);
		}
		this.redisStringCache.remove(captchaId, CacheType.CAPTCHA);

		// 校验用户名和密码
		User user = this.userMapper.queryUserByUid(uid);
		if (user == null){
			throw new BusinessException(ResultCode.NO_USER);
		}
		if (!password.equals(user.getPassword())){
			throw new BusinessException(ResultCode.PWD_ERROR);
		}

		// 生产token并将<token: Account-json格式数据>写入redis缓存
		String token = String.valueOf(this.idWorker.nextId());
		this.redisStringCache.set(token, JsonUtil.toJson(user), CacheType.ACCOUNT);

		return new Account(uid, null, token);
	}

	/* 校验token：如果存在重新写入redis，如果不存在直接返回false */
	public boolean checkToken(String token){
		// 入参校验
		if (StringUtils.isBlank(token)){
			return false;
		}

		String userInfo = this.redisStringCache.get(token, CacheType.ACCOUNT);
		if (StringUtils.isNotEmpty(userInfo)){
			this.redisStringCache.set(token, userInfo, CacheType.ACCOUNT);
			return true;
		} else {
			return false;
		}
	}

	/* 登出 */
	public void logout(String token) {
		this.redisStringCache.remove(token, CacheType.ACCOUNT);
	}

	/*public static void main(String[] args) {
		String x = null;
		String y = "ddd";
		System.out.println(y.equals(x));
		String z = "/login/pwdsetting";
		String[] arr = z.split("/");
		System.out.println(arr.length);
		System.out.println(arr[0].equals(""));
		for (String aa : arr){
			System.out.println(aa);
		}
	}*/

}
