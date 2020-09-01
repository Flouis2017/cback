package com.flouis.counter.service;

import com.flouis.common.usual.entity.ResultCode;
import com.flouis.counter.dao.UserMapper;
import com.flouis.counter.entity.User;
import com.flouis.counter.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

	@Resource
	private UserMapper userMapper;

	public void updatePassword(String uid, String oldPassword, String newPassword) throws BusinessException{
		User user = this.userMapper.queryUserByUid(uid);
		if (user == null){
			throw new BusinessException(ResultCode.NO_USER);
		}
		if (!user.getPassword().equals(oldPassword)){
			throw new BusinessException(ResultCode.OLD_PWD_ERROR);
		}
		User upOne = new User();
		upOne.setId(user.getId());
		upOne.setPassword(newPassword);
		this.userMapper.updateByPrimaryKeySelective(upOne);
	}
}
