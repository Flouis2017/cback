package com.flouis.counter.controller;

import com.flouis.common.usual.entity.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CounterExceptionHandler {

	@ExceptionHandler(Exception.class)
	public JsonResult error(Exception e){
		e.printStackTrace();
		return JsonResult.fail();
	}

}
