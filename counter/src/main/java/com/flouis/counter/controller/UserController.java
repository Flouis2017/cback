package com.flouis.counter.controller;

import com.flouis.common.usual.entity.JsonResult;
import com.flouis.counter.exception.BusinessException;
import com.flouis.counter.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	/**
	 * @description 修改密码
	 * @param uid
	 * @param oldPassword
	 * @param newPassword
	 */
	@RequestMapping("/password")
	public JsonResult updatePassword(String uid, String oldPassword, String newPassword) throws BusinessException {
		this.userService.updatePassword(uid, oldPassword, newPassword);
		return JsonResult.success();
	}

}
