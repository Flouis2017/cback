package com.flouis.common.usual.entity;

public enum ResultCode {

	SUCCESS(2000, "请求成功"),

	CODE_EXPIRED(3000, "验证码过期"),
	CODE_ERROR(3001, "验证码出错"),

	TOKEN_EXPIRED(3003, "登录超时"),

	NO_USER(3005, "用户账号不存在"),
	PWD_ERROR(3006, "密码出错"),

	LOGIN_INFO_BLANK(3010, "账号/密码/验证码不可为空"),
	LOGIN_ERROR(3011, "账号或密码出错"),

	NEED_LOGIN(3020, "请先登录"),
	NO_ACCESS(3021, "权限不足"),

	FAIL(4000, "服务器异常，请求失败！");

	private Integer code;
	private String message;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	ResultCode(Integer code, String message){
		this.setCode(code);
		this.setMessage(message);
	}

}
