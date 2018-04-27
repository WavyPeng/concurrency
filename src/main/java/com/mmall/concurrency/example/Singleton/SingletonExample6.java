package com.mmall.concurrency.example.Singleton;

import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 *
 */
@ThreadSafe
public class SingletonExample6 {
    // 私有的构造函数，防止外界随意调用
    private SingletonExample6(){

    }

    //单例对象（在类装载时进行创建）
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args){
        System.out.println(instance.getInstance().hashCode());
        System.out.println(instance.getInstance().hashCode());
    }
}