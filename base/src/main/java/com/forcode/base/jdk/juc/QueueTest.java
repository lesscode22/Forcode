package com.forcode.base.jdk.juc;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.*;

/**
 * 单向队列(BlockingQueue):
 *      ArrayBlockingQueue: 由数组结构支持的有界队列
 *      LinkedBlockingQueue: 由链表结构支持的可选有界队列
 *      PriorityBlockingQueue: 由最小二叉堆(优先级堆)结构支持的无界优先级队列
 *      DelayQueue: 由最小二叉堆(优先级堆)结构支持且基于时间的调度队列
 *      SynchronousQueue: 实现简单聚集(rendezvous)机制的同步阻塞交换队列(只存一个元素)
 *      LinkedTransferQueue: 由链表结构支持的无界队列
 *      DelayWorkQueue: 由最小二叉堆(优先级堆)结构支持的定时线程池定制版无界优先级队列
 *
 * 双向队列(BlockingDeque):
 *      LinkedBlockingDeque: 由链表结构支持的可选双向有界队列
 *
 * 非阻塞队列:
 *      ConcurrentLinkedQueue: 由链表结构支持的并发无界队列
 *      PriorityQueue: 由最小二叉堆(优先级堆)结构支持无界队列
 *      ConcurrentLinkedDeque: 由链表结构支持的并发双向无界队列
 *      ArrayDeque: 由数组结构支持的双向有界队列
 */
public class QueueTest {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("============= 优先队列");
        BlockingQueue<Integer> priorityQueue = new PriorityBlockingQueue<>();
        priorityQueue.offer(5);
        priorityQueue.offer(4);
        priorityQueue.offer(-1);
        priorityQueue.offer(3);
        priorityQueue.offer(6);
        System.out.println("从小到大排序: " + priorityQueue);

        System.out.println("============ 基于优先队列实现的延迟队列");
        BlockingQueue<WorkItem> delayQueue = new DelayQueue<>();
        delayQueue.add(new WorkItem("A", LocalDateTime.now().plus(3, ChronoUnit.SECONDS)));
        delayQueue.add(new WorkItem("B", LocalDateTime.now().plus(2, ChronoUnit.SECONDS)));
        delayQueue.add(new WorkItem("C", LocalDateTime.now().plus(1, ChronoUnit.SECONDS)));
        for (;;) {
            String name = delayQueue.take().name;
            System.out.println(name);
            if ("A".equals(name)) break;
        }

        System.out.println("============ LinkedTransferQueue ");
        // LinkedTransferQueue: 结合 SynchronousQueue 和 BlockingQueue
        //  相比 SynchronousQueue 多了可以存储的队列; 相比其他 BlockingQueue, 多了直接传递元素的功能(减少用锁同步)
        transferQueue();
    }

    private static void transferQueue() {
        // 扩展方法:
        //  tryTransfer: 将元素立刻给消费者, 立刻给一个等待接收元素的线程, 如果没有消费者就会返回false, 而不将元素放入队列
        //  transfer: 将元素给消费者, 如果没有消费者就会等待
        LinkedTransferQueue<Integer> queue = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(300);
                    System.out.println("consumer 消费: " + queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "consumer").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println("producer 生产数据: " + i);
                    queue.offer(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "producer").start();
    }

    static class WorkItem implements Delayed {

        private String name;
        private LocalDateTime targetTime;

        public WorkItem(String name, LocalDateTime time) {
            this.name = name;
            this.targetTime = time;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return Duration.between(LocalDateTime.now(), targetTime).getSeconds();
        }

        @Override
        public int compareTo(Delayed o) {
            WorkItem item = (WorkItem) o;
            if (targetTime.compareTo(item.targetTime) <= 0) {
                return -1;
            }
            return 1;
        }
    }
}
