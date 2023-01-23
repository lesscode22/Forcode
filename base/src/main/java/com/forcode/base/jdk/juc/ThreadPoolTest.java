package com.forcode.base.jdk.juc;

import java.util.concurrent.*;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-11-13
 **/
public class ThreadPoolTest {

    // 线程工厂实现 ThreadFactory 自定义
    // 拒绝策略实现 RejectedExecutionHandler 自定义
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 0,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
    }
}
