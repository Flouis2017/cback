package com.flouis.counter.exception;

import com.flouis.common.usual.entity.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends Exception {

	private Integer exceptionCode;
	private String exceptionMsg;

	public BusinessException(ResultCode resultCode){
		this(resultCode.getCode(), resultCode.getMessage());
	}

}
