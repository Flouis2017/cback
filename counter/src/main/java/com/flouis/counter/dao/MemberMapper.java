package com.flouis.counter.dao;

import com.flouis.counter.entity.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Member record);
}