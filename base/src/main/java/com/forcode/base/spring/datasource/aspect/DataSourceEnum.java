package com.forcode.base.spring.datasource.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

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
    QD("qd", "渠道"),
    ;

    private final String key;
    private final String describe;

    public static DataSourceEnum of(String key) {
        for (DataSourceEnum anEnum : DataSourceEnum.values()) {
            if (Objects.equals(anEnum.getKey(), key)) return anEnum;
        }
        return null;
    }
}
