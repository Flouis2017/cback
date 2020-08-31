package com.flouis.counter.exception;

import com.flouis.common.usual.entity.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CounterExceptionHandler {

	@ExceptionHandler(Exception.class)
	public JsonResult handleException(Exception e){
		e.printStackTrace();
		return JsonResult.fail();
	}

	@ExceptionHandler(BusinessException.class)
	public JsonResult handleBusinessException(BusinessException be){
		return JsonResult.fail(be.getExceptionCode(), be.getMessage());
	}

}
