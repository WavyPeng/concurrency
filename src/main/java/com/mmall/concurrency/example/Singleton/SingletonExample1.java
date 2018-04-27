package com.mmall.concurrency.example.Singleton;

import com.mmall.concurrency.annotations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletonExample1 {
    // 私有的构造函数，防止外界随意调用
    private SingletonExample1(){

    }

    //单例对象
    private static SingletonExample1 instance = null;

    //静态的工厂方法获取单例对象
    public static SingletonExample1 getInstance(){
        //这种方式在单线程下是可以的
        //但是在多线程下是线程不安全的
        //原因在于假设当两个线程同时访问下面if语句的内容时，可能因为null而分别各自进行实例化
        //实例化两次可能会造成一些不正确的结果
        if(instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }
}