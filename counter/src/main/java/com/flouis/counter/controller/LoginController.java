package com.flouis.counter.controller;

import com.flouis.common.usual.entity.JsonResult;
import com.flouis.common.usual.entity.ResultCode;
import com.flouis.counter.service.LoginService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
@Log4j2
public class LoginController {

	@Resource
	private LoginService loginService;

	@PostMapping("/captcha")
	public JsonResult captcha() throws Exception{
		return this.loginService.captcha();
	}

	@PostMapping("/doLogin")
	public JsonResult login(String uid, String password, String captcha, String captchaId) throws Exception{
		return JsonResult.success(this.loginService.login(uid, password, captcha, captchaId));
	}

	@RequestMapping("/checkToken")
	public JsonResult checkToken(String token){
		return JsonResult.success(this.loginService.checkToken(token));
	}

	@RequestMapping("/needLogin")
	public JsonResult needLogin(){
		return JsonResult.fail(ResultCode.NEED_LOGIN.getCode(), ResultCode.NEED_LOGIN.getMessage());
	}

	@RequestMapping("/logout")
	public JsonResult logout(String token){
		this.loginService.logout(token);
		return JsonResult.success();
	}

}
