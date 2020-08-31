package com.flouis.counter.dao;

import com.flouis.counter.entity.User;
import com.flouis.counter.vo.Account;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    User queryUser(Account account);

    User queryUserByUid(Long uid);
}