package com.flouis.counter.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchVo {

	private int currentPage = 1;
	private int pageSize = 10;

	private String order = "desc";

}
