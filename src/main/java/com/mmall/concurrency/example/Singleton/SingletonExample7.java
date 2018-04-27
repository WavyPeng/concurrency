package com.mmall.concurrency.example.Singleton;

import com.mmall.concurrency.annotations.Recommend;
import lombok.extern.slf4j.Slf4j;

/**
 * 采用枚举类实现单例模式
 * 最安全
 *
 * 推荐使用此方式，原因：
 * ①相比懒汉模式，在安全性方面更容易保证
 * ②相比饿汉模式，可以在实际的调用时才进行初始化
 */
@Slf4j
@Recommend
public class SingletonExample7 {
    // 私有构造函数
    private SingletonExample7(){

    }
    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    //定义枚举类
    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance(){
            return singleton;
        }
    }
}