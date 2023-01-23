package com.forcode.base.jdk.juc;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAccumulator;

public class CASTest {

    public static void main(String[] args) throws InterruptedException {

        // ABA问题解决方式:
        //  1.AtomicStampedReference: 时间戳控制, 能够完全解决
        //  2.AtomicMarkableReference: 维护boolean值, 不能完全杜绝ABA
        forStamped();

        // 扩展: 累加器
        forLongAccumulator();
    }

    /**
     * LongAdder自增是 LongAccumulator 累加器的一个特例
     */
    private static void forLongAccumulator() {
        System.out.println("========== 累加器");
        LongAccumulator num = new LongAccumulator((a, b) -> a * b, 1);
        for (int i = 1; i < 5; i++) {
            num.accumulate(2);
            System.out.println("累积乘法: " + num);
        }
    }

    private static void forStamped() throws InterruptedException {

        // 原始做法: 数据在中间被修改过, 最后依然能更新成功
        AtomicInteger atomic = new AtomicInteger(100);
        System.out.println("====== 原始数据: " + atomic.get());
        Thread t1 = new Thread(() -> {
            atomic.compareAndSet(100, 200);
            System.out.println("中途修改为: " + atomic.get());

            atomic.compareAndSet(200, 100);
            System.out.println("悄悄修改回去: " + atomic.get());
        });

        Thread t2 = new Thread(() -> {

            ThreadUtil.sleep(1);
            boolean b = atomic.compareAndSet(100, 101);
            System.out.println("原始值自增: " + atomic.get() + ", 更新是否成功: " + b);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // 增加时间戳版本控制
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);
        int initTime = stampedReference.getStamp();
        System.out.println("====== 时间戳控制, 原始数据: " + stampedReference.getReference());
        new Thread(() -> {
            stampedReference.compareAndSet(100, 103,
                    stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println("中途修改为: " + stampedReference.getReference());

            stampedReference.compareAndSet(103, 100,
                    stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println("悄悄修改回去: " + stampedReference.getReference());
        }).start();

        new Thread(() -> {

            ThreadUtil.sleep(1);
            boolean b = stampedReference.compareAndSet(100, 101,
                    initTime, initTime + 1);
            System.out.println("原始值自增: " + stampedReference.getReference() + ", 更新是否成功: " + b);
        }).start();

    }
}
