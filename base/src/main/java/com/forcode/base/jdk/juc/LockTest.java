package com.forcode.base.jdk.juc;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class LockTest {

    private static Lock lock = new ReentrantLock();
    private static Condition producerCondition = lock.newCondition();
    private static Condition consumerCondition = lock.newCondition();

    private static int initCount = 0;
    private static int maxCount = 3;

    public static void main(String[] args) {

//        forCountDownLatch();

//        forCyclicBarrier();

//        forSemaphore();

//        forPhaser();

        forExchanger();

//        IntStream.range(0, 10).forEach(el -> new Thread(LockTest::producer).start());
//        IntStream.range(0, 10).forEach(el -> new Thread(LockTest::consumer).start());
    }

    /**
     * 基于 AQS 共享模式实现
     */
    private static void forCountDownLatch() {
        /*
         *  1.多等一场景:
         *      初始count=1, 多条线程await()阻塞, 主线程调用countDown()唤醒所有阻塞线程
         *  2.一等多场景
         *      初始count=n, 多条线程调用countDown()对count减一, 一条线程await()阻塞, 当count=0时, 唤醒阻塞线程
         */
        CountDownLatch latch = new CountDownLatch(3);
        IntStream.range(0, 3).forEach(el -> {
            new Thread(() -> {
                ThreadUtil.sleep(100);
                System.out.println("===== 子线程执行");
                latch.countDown();
            }).start();
        });
        try {
            System.out.println(">>> 主线程开始等待");
            latch.await();
            System.out.println(">>> 主线程执行");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 基于 AQS 独占模式实现
     */
    private static void forCyclicBarrier() {
        // 多等多场景
        CyclicBarrier barrier = new CyclicBarrier(3);
        IntStream.range(0, 3).forEach(el -> {
            ThreadUtil.sleep(200);
            new Thread(() -> {
                try {
                    System.out.println("====== 子线程就绪");
                    barrier.await();
                    System.out.println("====== 子线程执行");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).start();
        });
    }

    private static Integer shareCount = 0;

    private static void forSemaphore() {
        Semaphore semaphore = new Semaphore(1);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(0, 12).forEach(el ->
                executorService.execute(() -> {
                    try {
                        // 获取许可
                        semaphore.acquire();
                        System.out.println("===== 获取到许可");
                        shareCount++;


                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        semaphore.release();
                        System.out.println("==== release ");
                    }

                })
        );

        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        System.out.println(">>>>>>>> shareCount = " + shareCount);
    }

    /**
     * <pre>
     * 适用场景:
     *  一个大任务分为多个阶段, 每个阶段的多个小任务可以并发执行, 但是必须等上一阶段执行完毕才能开始下一阶段
     * </pre>
     * <pre>
     * 概念:
     *     phase: 当前阶段索引, 从0开始
     *     parties: 建立的屏障数, 可以动态变化
     * </pre>
     * <pre>
     * 方法:
     *  arrive(): 非阻塞, 到达屏障点的数量+1,
     *  arriveAndDeregister(): 非阻塞, 到达屏障点的数量+1, 且取消一个屏障点注册, 返回当前阶段索引
     *  arriveAndAwaitAdvance(): 阻塞等到所有线程到达屏障点, 返回当前阶段索引
     *  awaitAdvance(int phase): 阻塞等待指定阶段下的其他线程到达屏障点
     * </pre>
     */
    private static void forPhaser() {
        // 注册三个屏障, 也相当于要监控的线程数
        Phaser phaser = new Phaser(3) {

            // 每一阶段结束的回调
            // true: phaser终止
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {

                System.out.println("第 " + phase + " 阶段结束! --------- 当前屏障数: " + registeredParties);
                return super.onAdvance(phase, registeredParties);
            }
        };

        // 三个线程并发执行, 上述注册了三个屏障
        for (int i = 0; i < 3; i++) {

            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " - 起跑准备, 当前阶段: " + phaser.getPhase());
                // 阻塞等待, 初始建立了三个屏障, 此时需要等待三个线程都到达
                // 且通过此屏障后则进入下一阶段, 阶段索引+1
                phaser.arriveAndAwaitAdvance();

                System.out.println(Thread.currentThread().getName() + " - 跑步中, 当前阶段: " + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();

                System.out.println(Thread.currentThread().getName() + " - 到达终点, 当前阶段: " + phaser.getPhase());
                phaser.arrive();
            }, i + "号").start();
        }
    }

    /**
     * <p>只能同步两个线程, 当第一个线程执行 exchange() 后, 会等待第二个线程执行到 exchange(), 然后交换数据
     */
    private static void forExchanger() {
        Exchanger<Integer> ex = new Exchanger<>();

        new Thread(() -> {

            Integer data = 10;
            System.out.println(Thread.currentThread().getName() + " 交换前 === " + data);
            try {
                data = ex.exchange(data);
                System.out.println(Thread.currentThread().getName() + " 交换后 >>> " + data);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "A").start();

        new Thread(() -> {

            Integer data = 22;
            System.out.println(Thread.currentThread().getName() + " 交换前 === " + data);
            try {
                data = ex.exchange(data);
                System.out.println(Thread.currentThread().getName() + " 交换后 >>> " + data);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "B").start();
    }

    private static void producer() {
        lock.lock();
        try {
            while (initCount >= maxCount) {
                System.out.println("库存已满: " + initCount);
                // 相当于库存已满, 先暂停生产
                producerCondition.awaitUninterruptibly();
            }

            System.out.println("======== 生产 ing =============");
            initCount++;
            // 已生产资源, 则唤醒消费者来消费
            consumerCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private static void consumer() {
        lock.lock();
        try {
            while (initCount <= 0) {
                System.out.println("库存不足: " + initCount);
                // 库存不足, 暂停消费
                consumerCondition.awaitUninterruptibly();
            }

            System.out.println("======== 消费 ing =============");
            initCount--;
            // 已消费资源, 代表库存还有位置, 唤醒生产者继续生产
            producerCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }


}
