package com.forcode.base.spring.datasource.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-11-11
 **/
@AllArgsConstructor
@Getter
public enum DataSourceEnum {

    DEFAULT("default", "默认数据源"),
    ORDER("order", "订单中心"),
    ;

    private final String key;
    private final String describe;
}
