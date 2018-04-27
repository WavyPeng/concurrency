package com.mmall.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * newScheduledThreadPool
 * Created by Wavy Peng on 2018/4/27.
 */
@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
//        executorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                log.info("schedule run");
//            }
//        },3, TimeUnit.SECONDS);
        // 以指定的速率去执行任务
        // 在任务放进线程池之后，延迟1秒之后，每隔3秒去执行一次任务
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("schedule run");
            }
        },1,3,TimeUnit.SECONDS);
//        executorService.shutdown();

        Timer timer = new Timer();
        //每次执行完成后，再间隔5秒继续执行
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer run");
            }
        },new Date(),5*1000);
    }
}