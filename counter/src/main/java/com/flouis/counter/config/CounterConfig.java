package com.flouis.counter.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CounterConfig {

	@Value("${counter.dataCenterId}")
	private long dataCenterId;

	@Value("${counter.workerId}")
	private long workerId;


	@Value("${cacheExpire.captcha}")
	private int ce;

	@Value("${cacheExpire.account}")
	private int ae;

	@Value("${cacheExpire.order}")
	private int oe;

}
