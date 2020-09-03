package com.flouis.counter.controller;

import com.flouis.common.usual.entity.JsonResult;
import com.flouis.counter.exception.BusinessException;
import com.flouis.counter.service.TransferService;
import com.flouis.counter.vo.TransferVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/transfer")
public class TransferController {

	@Resource
	private TransferService transferService;

	@PostMapping("/doTransfer")
	public JsonResult doTransfer(TransferVo vo) throws BusinessException {
		this.transferService.doTransfer(vo);
		return JsonResult.success("转账成功");
	}

}
