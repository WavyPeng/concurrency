package com.mmall.concurrency.example.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2 {
    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i=0;i<threadCount;i++){
            final int threadNum = i;
            exec.execute(()->{
                try{
                    test(threadNum);
                }catch (Exception e){
                    log.error("exception",e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        //主线程在等待
        //设置等待超时的时间，超过该时间就不再等待了
        //但是之前线程运行的任务还是会被执行完
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        exec.shutdown();
    }

    private static void test (int threadNum)throws Exception{
        Thread.sleep(100);
        log.info("{}",threadNum);
    }
}