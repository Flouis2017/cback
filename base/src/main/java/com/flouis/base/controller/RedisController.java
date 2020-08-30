package com.flouis.base.controller;

import com.flouis.base.service.RedisService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisController {

	@Resource
	private RedisService redisService;

	@RequestMapping("/set")
	public void set(String k, String v){
		this.redisService.set(k, v);
	}

	@RequestMapping("/getString/{k}")
	public String getString(@PathVariable String k){
		return this.redisService.getString(k);
	}

}
