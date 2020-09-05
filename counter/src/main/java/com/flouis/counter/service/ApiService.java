package com.flouis.counter.service;

import com.flouis.common.redis.util.CacheType;
import com.flouis.common.redis.util.RedisStringCache;
import com.flouis.common.usual.entity.ResultCode;
import com.flouis.common.usual.util.JsonUtil;
import com.flouis.counter.cache.StockCache;
import com.flouis.counter.dao.PosiMapper;
import com.flouis.counter.dao.UserMapper;
import com.flouis.counter.entity.Posi;
import com.flouis.counter.entity.User;
import com.flouis.counter.exception.BusinessException;
import com.flouis.counter.vo.DashboardVo;
import com.flouis.counter.vo.StockInfo;
import com.google.common.collect.Lists;
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

	@Resource
	private StockCache stockCache;

	public Map<String, Object> queryBalance(DashboardVo vo) throws BusinessException{
		User user = this.userMapper.queryUserByUid(vo.getUid());
		if (user == null){
			throw new BusinessException(ResultCode.NO_USER);
		}
		Map<String, Object> resMap = Maps.newHashMap();
		resMap.put("balance", user.getBalance());
		return resMap;
	}

	public List<Posi> queryPosiList(DashboardVo vo) throws BusinessException{
		List<Posi> posiList;
		String uid = vo.getUid();
		if (StringUtils.isBlank(uid)){
			throw new BusinessException(ResultCode.NO_USER);
		}

		if (vo.getIsRefresh()){
			posiList = this.posiMapper.queryPosiList(vo);
			this.redisStringCache.set(uid, JsonUtil.toJson(posiList), CacheType.POSI);
		} else {
			// 查询redis缓存持仓记录
			String redisPosi = this.redisStringCache.get(uid, CacheType.POSI);
			if (StringUtils.isEmpty(redisPosi)){
				// 数据查询，然后缓存
				posiList = this.posiMapper.queryPosiList(vo);
				this.redisStringCache.set(uid, JsonUtil.toJson(posiList), CacheType.POSI);
			} else {
				// 序列化成List<Posi>
				posiList = JsonUtil.fromJsonArr(redisPosi, Posi.class);
			}
		}
		return posiList;
	}

	public Map posiData(DashboardVo vo) throws BusinessException{
		Map resMap = Maps.newHashMap();
		String uid = vo.getUid();
		User user = this.userMapper.queryUserByUid(uid);
		if (user == null){
			throw new BusinessException(ResultCode.NO_USER);
		}
		resMap.put("balance", user.getBalance());
		// 分页查询数据
		vo.setCurrentPage(vo.getPageSize() * (vo.getCurrentPage() - 1));
		List<Posi> posiList = this.posiMapper.queryPosiListByPage(vo);
		Integer total = this.posiMapper.queryPosiTotal(vo);
		// 封装结果
		resMap.put("total", total);
		resMap.put("posiList", posiList);
		return resMap;
	}

	public List<StockInfo> stockSelect(String uid, String key) {
		return this.stockCache.getStocks(uid, key);
	}
}
