package com.mmall.concurrency.example.lock;

import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

@Slf4j
@ThreadSafe
public class LockExample5 {

    // 请求总数
    public static int clientTotal = 5000;
    // 同时并发执行的线程数
    public static int threadTotal = 200;
    //
    public static int count = 0;

    private final static StampedLock lock = new StampedLock();

    public static void main(String[] args) throws Exception{
        // 定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 定义计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    // 引入信号量
                    semaphore.acquire();
                    add();
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
        log.info("count:{}",count);
    }

    //这个方法是线程不安全的写法
    private static void add(){
        long stamp = lock.writeLock();
        try{
            count++;
        }finally {
            lock.unlock(stamp);
        }

    }
}