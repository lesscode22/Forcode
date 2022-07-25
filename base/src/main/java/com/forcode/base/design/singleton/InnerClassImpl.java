package com.forcode.base.design.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 静态内部类实现
 * 
 * @author: TJ
 * @date:  2022-06-03
 **/
public class InnerClassImpl {

    private InnerClassImpl() {}

    private static class SingletonHolder {
        private static InnerClassImpl instance = new InnerClassImpl();
    }

    public static InnerClassImpl getInstance() {
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                System.out.println(InnerClassImpl.getInstance());
            });
        }
        System.out.println("===");
        threadPool.shutdown();
    }
}
