package com.flouis.counter.dao;

import com.flouis.counter.entity.HisTrade;

public interface HisTradeMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(HisTrade record);

    HisTrade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisTrade record);
}