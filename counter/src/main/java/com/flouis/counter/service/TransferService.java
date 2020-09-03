package com.flouis.counter.service;

import com.flouis.common.usual.entity.ResultCode;
import com.flouis.counter.dao.TransferMapper;
import com.flouis.counter.dao.UserMapper;
import com.flouis.counter.entity.User;
import com.flouis.counter.exception.BusinessException;
import com.flouis.counter.vo.TransferVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class TransferService {

	@Resource
	private UserMapper userMapper;

	@Resource
	private TransferMapper transferMapper;

	public void doTransfer(TransferVo vo) throws BusinessException{
		User user = this.userMapper.queryUserByUid(vo.getUid());
		if (user == null){
			throw new BusinessException(ResultCode.NO_USER);
		}

		// 校验密码是否正确
		if (!user.getPassword().equals(vo.getPassword())){
			throw new BusinessException(ResultCode.PWD_ERROR);
		}

		User upOne = new User();
		upOne.setId(user.getId());
		// 将用户的余额进行增减
		Integer type = vo.getType();
		BigDecimal balance = user.getBalance();
		if (type.equals(0)){
			if (balance.compareTo(vo.getMoney()) < 0){
				throw new BusinessException(ResultCode.NO_ENOUGH_BALANCE);
			}
			upOne.setBalance(balance.subtract(vo.getMoney()));
		} else {
			upOne.setBalance(balance.add(vo.getMoney()));
		}
		this.userMapper.updateByPrimaryKeySelective(upOne);

		// 保存转账记录
		this.transferMapper.insertSelective(TransferVo.toEntity(vo));
	}

}
