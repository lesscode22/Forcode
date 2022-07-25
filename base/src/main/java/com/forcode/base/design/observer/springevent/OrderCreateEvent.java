package com.forcode.base.design.observer.springevent;

import org.springframework.context.ApplicationEvent;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-06-04
 **/
public class OrderCreateEvent extends ApplicationEvent {

    public OrderCreateEvent(Object source) {
        super(source);
    }
}
