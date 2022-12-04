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
public enum OrderChangeEvent {

    PAYED(1, "支付"),
    DELIVERY(2, "发货"),
    RECEIVED(3, "确认收货");

    private final int type;
    private final String name;
}
