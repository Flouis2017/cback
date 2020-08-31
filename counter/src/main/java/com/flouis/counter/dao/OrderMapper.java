package com.flouis.counter.dao;

import com.flouis.counter.entity.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);
}