package com.mmall.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 修饰一个代码块
 * 说明：
 * ①同步代码块作用于当前对象，不同调用对象之间是互相不影响的
 * ②同步方法作用于当前对象，不同调用对象之间是互相不影响的
 * 总结：如果一个方法内部是一个完整的同步代码块，那么其和用synchronized修饰的代码块是等同的
 * 注意：如果当前类为一个父类，子类继承该类后，调用test2()方法是不带synchronized关键字的，
 * 因为synchronized不属于方法声明的一部分。子类若想使用synchronized，则需要自行在方法上显式地声明synchronized
 *
 * Created by Wavy Peng on 2018/4/19.
 */
@Slf4j
public class SynchronizedExample1 {

    // 修饰一个代码块
    public void test1(int j){
        synchronized (this){
            for(int i=0;i<10;i++){
                log.info("test1 {}-{}",j,i);
            }
        }
    }

    // 修饰一个方法
    public synchronized void test2(int j){
        for(int i=0;i<10;i++){
            log.info("test2 {}-{}",j,i);
        }
    }

    public static void main(String[] args){
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        // 声明一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test2(1);
        });
        executorService.execute(()->{
            example2.test2(2);
        });
    }
}