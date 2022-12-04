package com.forcode.base.design.fsm.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-11-16
 **/
@AllArgsConstructor
@Getter
public enum OrderStatusEnum {

    WAIT_PAYMENT(1, "待支付"),
    WAIT_DELIVER(2, "带发货"),
    WAIT_RECEIVE(3, "待收货"),
    FINISH(4, "订单完成");

    private final int type;
    private final String name;
}
