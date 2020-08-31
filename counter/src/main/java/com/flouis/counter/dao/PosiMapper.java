package com.flouis.counter.dao;

import com.flouis.counter.entity.Posi;

public interface PosiMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Posi record);

    Posi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Posi record);
}