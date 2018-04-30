package com.mmall.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition的使用
 * Created by WavyPeng on 2018/4/30.
 */
@Slf4j
public class LockExample6 {
    public static void main(String[] args) {
        //定义一个ReentrantLock
        ReentrantLock reentrantLock = new ReentrantLock();
        //从ReentrantLock中获取Condition
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();  //线程加入到AQS的等待队列里
                log.info("wait signal"); // 1
                condition.await();     //线程从AQS中移除，对应操作是锁的释放，
                                       // 之后加入Condition的等待队列中
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); // 4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            //线程2由于线程1释放锁被唤醒，并判断是否可以取到锁
            //于是线程二获取锁，也加入到AQS的等待队列中
            reentrantLock.lock();
            log.info("get lock"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();  //调用condition发送信号的方法
                                    // 这时等待队列中有线程1的节点，于是被取出加入AQS的等待队列
                                    // 注意，此时线程1并未被唤醒
            log.info("send signal ~ "); // 3
            reentrantLock.unlock(); // 释放锁，唤醒线程1，线程1继续执行
        }).start();
    }
}
