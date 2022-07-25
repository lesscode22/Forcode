package com.forcode.base.design.observer.springevent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-06-04
 **/
@Component
public class OrderCreateEventListener {

    /**
     * 1.使用 @TransactionalEventListener 注解监听可实现事务隔离
     * 2.增加 @Async注解可异步执行
     * 3.增加 @Order注解可指定执行顺序
     * 4.使用 condition属性可使用el表达式过滤事件
     */

    @EventListener
    public void sendWx(OrderCreateEvent event) {
        System.out.println("发送微信消息: " + event);
    }

    @EventListener
    public void sendSms(OrderCreateEvent event) {
        System.out.println("发送短信: " + event);
    }
}
