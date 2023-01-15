package com.forcode.base.jdk.juc;

import cn.hutool.core.thread.ThreadUtil;
import org.openjdk.jol.info.ClassLayout;

import java.util.stream.IntStream;

/**
 * ClassLayout.parseInstance(object).toPrintable(): 查看对象内部信息
 * GraphLayout.parseInstance(object).toPrintable(): 查看对象外部信息, 包括引用的对象
 * GraphLayout.parseInstance(object).totalSize(): 查看对象总大小
 */
public class SynchronizedTest {

    public static void main(String[] args) {

        Object obj = new Object();
        System.out.println("========== 无锁状态 =============");
        // 1. 无锁态: 虚拟机刚启动时 new 出来的对象处于无锁状态
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        ThreadUtil.sleep(4000);

        System.out.println("========== 匿名偏向锁 =============");
        // 2.匿名偏向锁: 休眠4s后再创建出来的对象处于匿名偏向锁状态
        // 此状态下, 标志位为偏向锁但是对象头中 MarkWord 中的 threadId 为空
        Object obj1 = new Object();
        System.out.println(ClassLayout.parseInstance(obj1).toPrintable());

        System.out.println("========== 轻量级锁 =============");
        // 3.无锁状态对象加锁之后直接变为轻量级锁
        synchronized (obj) {
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }

        // 4.重量级锁状态
        IntStream.range(0, 3).forEach(el -> {
            new Thread(() -> {
                synchronized (obj) {
                    System.out.println("======== 重量级锁 ===========");
                    System.out.println(ClassLayout.parseInstance(obj).toPrintable());
                }
            }).start();
        });

        ThreadUtil.sleep(5000);
        System.out.println("======== 重量级锁之后 ===========");
        // 5.变为无锁态
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        System.out.println("======== 最后加锁 ===========");
        // 6.又升级为轻量级锁
        synchronized (obj) {
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
    }
}
