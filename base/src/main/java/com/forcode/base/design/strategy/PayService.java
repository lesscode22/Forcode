package com.forcode.base.design.strategy;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 提供支付服务
 */
@Service("payService")
public class PayService implements ApplicationContextAware {

    /**
     * 存储所有的支付实现
     */
    private static final Map<String, IPay> PAY_MAP = Maps.newHashMap();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 获取带有指定注解的bean, key为bean名称, value为bean实例
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(PayCode.class);
        beansWithAnnotation.forEach((key, value) -> {
            PayCode annotation = value.getClass().getAnnotation(PayCode.class);
            PAY_MAP.put(annotation.code(), (IPay) value);
        });
    }

    public void pay(String code) {
        PAY_MAP.get(code).pay();
    }
}
