package com.flouis.common.usual.entity;

import lombok.Data;

import java.util.List;

@Data
public class PageResult {

	private Long total;
	private List<Object> rows;

}
