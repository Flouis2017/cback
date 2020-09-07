package com.flouis.common.usual.entity.order;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@ToString
public class OrderCmd implements Serializable {

    private CmdType cmdType;

    /**
     * 会员ID
     */
    private String mid;

    /**
     * 用户ID
     */
    private String uid;

    /**
     * 代码
     */
    private String code;

    /**
     * 方向
     */
    private OrderDirection direction;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 量
     */
    private Integer count;

    /**
     * 委托类型
     * 1.LIMIT
     */
    private OrderType orderType;

    private Integer status;

    /**
     * 委托编号
     */
    private Long oid;

}
