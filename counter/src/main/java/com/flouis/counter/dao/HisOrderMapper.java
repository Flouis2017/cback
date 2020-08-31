package com.flouis.counter.dao;

import com.flouis.counter.entity.HisOrder;

public interface HisOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(HisOrder record);

    HisOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisOrder record);
}