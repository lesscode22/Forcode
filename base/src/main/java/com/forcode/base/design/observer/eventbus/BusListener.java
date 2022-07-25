package com.forcode.base.design.observer.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @description:
 * 1.内部使用反射执行订阅方法
 * 2.根据参数类型匹配符合的订阅方法
 * 3.可配置线程池使订阅方法异步执行
 *
 * @author: TJ
 * @date:  2022-06-04
 **/
public class BusListener {

    @Subscribe
    public void action1(Object event) {
        System.out.println("action1 --- " + event);
    }

    @Subscribe
    public void action2(String event) {
        System.out.println("action2 --- " + event);
    }

    @Subscribe
    public void action3(Integer event) {
        System.out.println("action3 --- " + event);
    }
}
