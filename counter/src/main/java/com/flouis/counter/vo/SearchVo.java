package com.flouis.counter.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchVo {

	private int page = 1;
	private int size = 10;

	private String order = "desc";

}
