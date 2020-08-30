package com.flouis.base.service;

import com.flouis.base.dao.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DbService {

	@Resource
	private TestMapper testMapper;

	public Integer getCount(){
		return this.testMapper.queryCount();
	}

}
