package com.forcode.base.design.observer.springevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-06-04
 **/
@Service
public class OrderService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void createOrder() {
        System.out.println("创建订单...");

        // 发布订单创建事件
        Map<String, Object> param = new HashMap<>();
        param.put("user", "AAA");
        applicationEventPublisher.publishEvent(new OrderCreateEvent(param));
    }
}
