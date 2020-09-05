package com.flouis.counter.controller;

import com.flouis.common.usual.entity.JsonResult;
import com.flouis.counter.exception.BusinessException;
import com.flouis.counter.service.ApiService;
import com.flouis.counter.vo.DashboardVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Resource
	private ApiService apiService;

	/**
	 * @description 用户资金
	 */
	@RequestMapping("/queryBalance")
	public JsonResult queryBalance(DashboardVo vo) throws BusinessException {
		Map<String, Object> map = this.apiService.queryBalance(vo);
		return JsonResult.success(map);
	}

	/**
	 * @description 资金持仓列表
	 */
	@RequestMapping("/queryPosiList")
	public JsonResult queryPosiList(DashboardVo vo) throws BusinessException {
		return JsonResult.success(this.apiService.queryPosiList(vo));
	}

	/**
	 * @description 资金持仓数据——真分页使用
	 */
	@PostMapping("/posiData")
	public JsonResult posiData(DashboardVo vo) throws BusinessException{
		return JsonResult.success(this.apiService.posiData(vo));
	}




}
