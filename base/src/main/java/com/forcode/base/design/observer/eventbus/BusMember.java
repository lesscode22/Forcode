package com.forcode.base.design.observer.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-06-04
 **/
public class BusMember {

    private ExecutorService executorService = Executors.newFixedThreadPool(2);
    private EventBus eventBus = new AsyncEventBus(executorService);

    public void register() {
        System.out.println("会员注册成功");

        // 调用观察者工作
        eventBus.post(new ArrayList<>());
    }

    public static void main(String[] args) {
        BusMember member = new BusMember();
        // 注册观察者
        member.eventBus.register(new BusListener());
        member.register();
        member.executorService.shutdown();
    }
}
