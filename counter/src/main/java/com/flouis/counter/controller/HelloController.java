package com.flouis.counter.controller;

import com.flouis.common.usual.entity.JsonResult;
import com.flouis.counter.client.BaseClient;
import com.flouis.counter.dao.TestMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/hello")
public class HelloController {

	@Resource
	private TestMapper testMapper;

	@Resource
	private BaseClient baseClient;

	@GetMapping("/{name}")
	public String hello(@PathVariable String name){
		return "hello " + name + "!";
	}

	@GetMapping("/getCount")
	public String getCount(){
		return String.valueOf(this.baseClient.dbGetCount());
	}

	@GetMapping("/getCount2")
	public JsonResult getCount2(){
		int res = this.testMapper.queryCount();
		return JsonResult.success(res);
	}

	@GetMapping("/testRedis")
	public String testRedis(){
		this.baseClient.redisSet("testRedis", "Hello Redis~");
		return this.baseClient.redisGetString("testRedis");
	}

}
