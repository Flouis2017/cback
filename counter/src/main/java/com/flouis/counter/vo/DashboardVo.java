package com.flouis.counter.vo;

import lombok.Data;

@Data
public class DashboardVo extends SearchVo{

	private String uid;

	private Boolean isRefresh = false;

}
