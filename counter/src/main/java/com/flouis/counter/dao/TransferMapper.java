package com.flouis.counter.dao;

import com.flouis.counter.entity.Transfer;

public interface TransferMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Transfer record);

    Transfer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Transfer record);
}