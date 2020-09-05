package com.flouis.counter.cache;

import com.flouis.common.redis.util.CacheType;
import com.flouis.common.redis.util.RedisStringCache;
import com.flouis.common.usual.util.JsonUtil;
import com.flouis.counter.dao.StockMapper;
import com.flouis.counter.entity.Stock;
import com.flouis.counter.vo.StockInfo;
import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Log4j2
public class StockCache {

	@Resource
	private RedisStringCache redisStringCache;

	@Resource
	private StockMapper stockMapper;

	public List<StockInfo> getStocks(String uid, String key){
		List<StockInfo> resList = Lists.newArrayList();
		List<Stock> allStockList;
		// 查询redis缓存中的股票
		String allStockStr = this.redisStringCache.get(uid, CacheType.STOCK);
		if (StringUtils.isEmpty(allStockStr)){
			// 从数据库获取所有股票信息并缓存
			allStockList = this.stockMapper.queryAll();
			this.redisStringCache.set(uid, JsonUtil.toJson(allStockList), CacheType.STOCK);
		} else {
			allStockList = JsonUtil.fromJsonArr(allStockStr, Stock.class);
		}

		// 进行模糊搜索
		for (Stock stock : allStockList){
			if (stock.getCode().contains(key) || stock.getAbbrname().contains(key)){
				resList.add(new StockInfo(stock.getCode(), stock.getName(), stock.getAbbrname()));
			}
		}

		return resList;
	}


}
