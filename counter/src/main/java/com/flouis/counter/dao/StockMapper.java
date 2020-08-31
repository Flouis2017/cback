package com.flouis.counter.dao;

import com.flouis.counter.entity.Stock;

public interface StockMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Stock record);
}