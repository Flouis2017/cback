package com.flouis.counter.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderVo {

	private String uid;

	private Integer cmdType;

	private String code;

	private Integer direction;

	private BigDecimal price;

	private Integer count;

	private Integer orderType;

}
