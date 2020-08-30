package com.flouis.base.controller;

import com.flouis.base.service.DbService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/db")
public class DbController {

	@Resource
	private DbService dbService;

	@RequestMapping("/getCount")
	public String getCount(){
		return this.dbService.getCount() + "";
	}

}
