package com.flouis.common.usual.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonResult {

	private Boolean flag; // 请求是否成功
	private Integer code; // 返回码
	private String message; // 消息说明
	private Object data; // 数据集

	private JsonResult(Boolean flag, Integer code, String message, Object data){
		this.setFlag(flag);
		this.setCode(code);
		this.setMessage(message);
		this.setData(data);
	}

	public static JsonResult generateResult(Boolean flag, Integer code, String message, Object data){
		return new JsonResult(flag, code, message, data);
	}

	public static JsonResult success(){
		return new JsonResult(true, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
	}

	public static JsonResult success(Object data){
		return new JsonResult(true, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
	}

	public static JsonResult fail(){
		return new JsonResult(false, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage(), null);
	}

	public static JsonResult fail(String message){
		return new JsonResult(false, ResultCode.FAIL.getCode(), message, null);
	}

}
