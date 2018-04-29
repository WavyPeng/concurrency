package com.mmall.concurrency.example.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample3 {
    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);

        for(int i=0;i<threadCount;i++){
            final int threadNum = i;
            exec.execute(()->{
                try{
                    if(semaphore.tryAcquire()){ //尝试获取一个许可
                        test(threadNum);
                        semaphore.release();  //释放一个许可
                    }
                }catch (Exception e){
                    log.error("exception",e);
                }
            });
        }
        //主线程在等待
        log.info("finish");
        exec.shutdown();
    }

    private static void test (int threadNum)throws Exception{
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}