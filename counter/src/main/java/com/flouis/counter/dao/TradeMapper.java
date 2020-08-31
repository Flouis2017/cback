package com.flouis.counter.dao;

import com.flouis.counter.entity.Trade;

public interface TradeMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Trade record);

    Trade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Trade record);
}