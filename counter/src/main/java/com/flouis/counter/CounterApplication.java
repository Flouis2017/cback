package com.flouis.counter;

import com.flouis.common.redis.util.RedisStringCache;
import com.flouis.common.usual.util.SnowflakeIdWorker;
import com.flouis.counter.config.CounterConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
@MapperScan("com.flouis.counter.dao")
@EnableFeignClients
public class CounterApplication {

	@Resource
	private CounterConfig counterConfig;

	public static void main(String[] args) {
		SpringApplication.run(CounterApplication.class, args);
	}

	@Bean
	public RedisStringCache redisStringCache(){
		return new RedisStringCache(counterConfig.getCe(), counterConfig.getAe(), counterConfig.getOe());
	}

	@Bean
	public SnowflakeIdWorker idWorker(){
		return new SnowflakeIdWorker(counterConfig.getWorkerId(), counterConfig.getDataCenterId());
	}

}
