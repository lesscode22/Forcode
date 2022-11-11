package com.forcode.base.spring.datasource.aspect;

import java.lang.annotation.*;

/**
 * @description: 数据源切换
 * 
 * @author: TJ
 * @date:  2022-11-07
 **/
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DS {

    DataSourceEnum value() default DataSourceEnum.DEFAULT;
}
