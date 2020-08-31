package com.flouis.counter.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {

	private Long uid;
	private String password;

	private String token;

}
