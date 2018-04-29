package com.mmall.concurrency.example.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * Created by WavyPeng on 2018/4/29.
 */
@Slf4j
public class CyclicBarrierExample1 {

    //定义一个值，说明当前共有多少个线程需要同步等待
    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception{
        //定义一个线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(()->{
                try {
                    race(threadNum);
                }catch (Exception e){
                    log.error("exception",e);
                }
            });
        }
        executor.shutdown();
    }

    //模拟多个线程之间进行赛跑
    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        //调用await()方法，说明当前自己已经准备好了
        barrier.await();
        //当达到共同等待的线程数目时，就可以继续执行下面这句话了
        log.info("{} continue",threadNum);
    }
}
