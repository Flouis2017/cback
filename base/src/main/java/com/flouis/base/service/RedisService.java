package com.flouis.base.service;

import com.flouis.base.util.RedisUtil;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	public void set(String k, String v){
		RedisUtil.set(k, v);
	}

	public String getString(String k){
		return RedisUtil.getString(k);
	}

}
