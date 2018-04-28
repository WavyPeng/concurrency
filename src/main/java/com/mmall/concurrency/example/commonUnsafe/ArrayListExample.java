package com.mmall.concurrency.example.commonUnsafe;

import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * ArrayList线程不安全演示
 * Created by WavyPeng on 2018/4/28.
 */
@Slf4j
@NotThreadSafe
public class ArrayListExample {
    // 请求总数
    public static int clientTotal = 5000;
    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static ArrayList<Integer> list = new ArrayList<>();

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
        log.info("{}",list.size());
    }


    private static void update(int i){
        list.add(i);
    }
}
