package com.mmall.concurrency.example.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

@Slf4j
public class ConcurrentSkipListSetExample {
    // 请求总数
    public static int clientTotal = 5000;
    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static Set<Integer> set = new ConcurrentSkipListSet<>();

    public static void main(String[] args) throws Exception{
        // 定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 定义计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            final int count = i;
            executorService.execute(()->{
                try {
                    // 引入信号量
                    semaphore.acquire();
                    update(count);
                    // 释放信号量
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        //关闭线程池
        executorService.shutdown();
        log.info("count:{}", set.size());
    }

    //这个方法是线程不安全的写法
    private static void update(int i){
        set.add(i);
    }
}