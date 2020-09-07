package com.flouis.counter.vo;

import com.flouis.common.usual.entity.order.CmdType;
import com.flouis.common.usual.entity.order.OrderCmd;
import com.flouis.common.usual.entity.order.OrderDirection;
import com.flouis.common.usual.entity.order.OrderType;
import com.flouis.counter.entity.Order;

public class CounterAdaptor {

	public static OrderCmd convert2OrderCmd(OrderVo vo){
		OrderCmd orderCmd = OrderCmd.builder()
				.cmdType(CmdType.of(vo.getCmdType()))
				.mid("10000")
				.uid(vo.getUid())
				.code(vo.getCode())
				.direction(OrderDirection.of(vo.getDirection()))
				.price(vo.getPrice())
				.count(vo.getCount())
				.orderType(OrderType.of(vo.getOrderType()))
				.build();
		return orderCmd;
	}

	public static Order convert2Order(OrderCmd orderCmd){
		Order order = new Order();
		order.setUid(orderCmd.getUid());
		order.setCode(orderCmd.getCode());
		order.setDirection(orderCmd.getDirection().getDirection());
		order.setType(orderCmd.getOrderType().getType());
		order.setPrice(orderCmd.getPrice());
		order.setCount(orderCmd.getCount());
		return order;
	}

}
