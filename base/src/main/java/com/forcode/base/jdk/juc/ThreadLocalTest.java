package com.forcode.base.jdk.juc;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadLocalTest {

    // 黄金分割数
    private static final int HASH_INCREMENT = 0x61c88647;
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    // ttl: 线程池中上下文传递
    private static TransmittableThreadLocal ttl = new TransmittableThreadLocal();
    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {

//        hashIncrement();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) executorService;
        executor.prestartAllCoreThreads();

        ttl.set("<parent data>");
        Runnable task = () -> System.out.println("子线程获取上下文: " + ttl.get());
        executor.execute(TtlRunnable.get(task));

    }

    /**
     * 每创建一个 ThreadLocal 对象, hash增量都为黄金分割数
     * 带来的好处是 hash 分布均匀
     * 源码计算方式: firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
     */
    private static void hashIncrement() {
        List<Integer> dataList = IntStream.range(0, 15)
                .mapToObj(i -> ((i + 1) * HASH_INCREMENT) & 15)
                .collect(Collectors.toList());
        System.out.println("桶中数据分布: " + dataList);
    }
}
