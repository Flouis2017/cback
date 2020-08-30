package com.flouis.counter.controller;

import com.flouis.common.usual.entity.JsonResult;
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


}
