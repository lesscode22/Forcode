package com.forcode.base.jdk.juc;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.CompletableFuture;

/**
 * 方法名带 Async: 具体执行任务线程视情况而定
 *      (1) 上个任务已经执行结束, 则交由上个任务的执行线程继续执行
 *      (2) 上个任务还未结束, 则另起线程执行
 *      (3) 若创建任务指定了线程池, 则使用线程池中线程去执行
 * 方法名不带 Async: 统一由上个任务的执行线程继续执行
 *
 * get(): 阻塞线程获取执行结果(与FutureTask.get()一致)
 * getNow(T valueIfAbsent): 尝试获取执行结果, 执行完成返回执行结果, 未完成返回指定值
 * join(): 阻塞线程等待任务执行结束
 * whenCompleteAsync(): 任务执行结束后的回调
 */
public class CompletableFutureTest {

    public static void main(String[] args) {

        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            ThreadUtil.sleep(500);
            System.out.println("=== 异步执行 ===");
            int i = 1 / 0;
            return "异步结果";
        });
        // 执行成功回调
        supplyAsync.thenAccept(result -> {
            System.out.println("回调获取数据: " + result);
        });
        // 执行异常回调
        supplyAsync.exceptionally(throwable -> {
            System.out.println("异常信息: " + throwable.getMessage());
            return null;
        });
        supplyAsync.whenCompleteAsync((result, ex) -> {
            System.out.println("=== 子任务任务执行完成 ====");
        });

        for (int i = 1; i <= 10; i++){
            System.out.println("main线程 - 输出: " + i);
            ThreadUtil.sleep(200);
        }
    }
}
