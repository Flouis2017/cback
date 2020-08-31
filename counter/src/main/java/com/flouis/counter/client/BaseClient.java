package com.flouis.counter.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "base")
public interface BaseClient {

	@RequestMapping("/redis/set")
	void redisSet(@RequestParam(name = "k") String k, @RequestParam(name = "v") String v);

	@RequestMapping("/redis/getString/{k}")
	String redisGetString(@PathVariable(name = "k") String k);

	@RequestMapping("/db/getCount")
	String dbGetCount();

}
