package com.forcode.base.spring.datasource.aspect;

import com.forcode.base.spring.datasource.DataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description: 数据源切换AOP
 * 
 * @author: TJ
 * @date:  2022-11-07
 **/
@Aspect
@Order(1)
@Component
@Slf4j
public class DataSourceAspect {

    @Pointcut("@annotation(com.forcode.base.spring.datasource.aspect.DS)")
    public void point() {

    }

    @Around("point()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        Method method = ((MethodSignature) point.getSignature()).getMethod();
        DS ds = method.getAnnotation(DS.class);
        log.info("[数据源切换]: " + ds.value());
        DataSourceHolder.setDataSource(ds.value());

        try {
            return point.proceed();
        } finally {
            DataSourceHolder.clearDataSource();
        }
    }
}
