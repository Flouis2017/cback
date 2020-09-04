package com.flouis.counter.service;

import com.flouis.common.redis.util.CacheType;
import com.flouis.common.redis.util.RedisStringCache;
import com.flouis.common.usual.entity.ResultCode;
import com.flouis.common.usual.util.JsonUtil;
import com.flouis.counter.dao.PosiMapper;
import com.flouis.counter.dao.UserMapper;
import com.flouis.counter.entity.Posi;
import com.flouis.counter.entity.User;
import com.flouis.counter.exception.BusinessException;
import com.flouis.counter.vo.DashboardVo;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ApiService {

	@Resource
	private RedisStringCache redisStringCache;

	@Resource
	private UserMapper userMapper;

	@Resource
	private PosiMapper posiMapper;

	public List<Posi> queryPosiList(DashboardVo vo) throws BusinessException{
		String uid = vo.getUid();
		if (StringUtils.isBlank(uid)){
			throw new BusinessException(ResultCode.NO_USER);
		}
		// 查询redis缓存持仓记录
		String redisPosi = this.redisStringCache.get(uid, CacheType.POSI);
		if (StringUtils.isEmpty(redisPosi)){
			// 数据查询
			List<Posi> posiList = this.posiMapper.queryPosiList(vo);
			// 缓存
			this.redisStringCache.set(uid, JsonUtil.toJson(posiList), CacheType.POSI);
			return posiList;
		} else {
			// 序列化成List<Posi>
			return JsonUtil.fromJsonArr(redisPosi, Posi.class);
		}
	}

	public Map<String, Object> queryBalance(DashboardVo vo) throws BusinessException{
		User user = this.userMapper.queryUserByUid(vo.getUid());
		if (user == null){
			throw new BusinessException(ResultCode.NO_USER);
		}
		Map<String, Object> resMap = Maps.newHashMap();
		resMap.put("balance", user.getBalance());
		return resMap;
	}
}
