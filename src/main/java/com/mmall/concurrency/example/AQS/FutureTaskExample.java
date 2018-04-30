package com.mmall.concurrency.example.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by WavyPeng on 2018/4/30.
 */
@Slf4j
public class FutureTaskExample {
    public static void main(String[] args) throws Exception{
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });

        new Thread(futureTask).start();
        log.info("do something in main");
        Thread.sleep(1000);
        String result = futureTask.get();//如果之前的方法没结束的话，会一直阻塞在这里
        log.info("result:{}",result);
    }
}
