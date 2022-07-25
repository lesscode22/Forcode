package com.forcode.base.design.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 双重检测锁
 * 
 * @author: TJ
 * @date:  2022-06-03
 **/
public class DCLImpl {

    private DCLImpl() {}

    private static Integer count = 0;

    private static volatile DCLImpl instance;

    public static DCLImpl getInstance() {

        if (instance == null) {
            synchronized (DCLImpl.class) {
                if (instance == null) {
                    instance = new DCLImpl();
                    count++;
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                System.out.println(DCLImpl.getInstance());
            });
        }
        System.out.println("===");
        System.out.println(count);

        threadPool.shutdown();
    }
}
