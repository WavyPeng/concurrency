package com.mmall.concurrency.example.Singleton;

import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建，由于在类装载时进行创建，因此可以保证线程安全
 * 如果类的构造函数没有包含过多的处理，饿汉模式是可以接受的
 *
 * 不足：
 * 如果构造函数中包含过多的处理，将会导致类加载时非常慢。因此可能会引起性能问题
 * 使用饿汉模式只进行类的加载而没有实际的调用的话，将会造成资源的浪费
 * 因此，使用饿汉模式要考虑两个问题：
 * ① 类的私有构造函数在实现时没有过多的处理
 * ② 类会被使用
 */
@ThreadSafe
public class SingletonExample2 {
    // 私有的构造函数，防止外界随意调用
    private SingletonExample2(){

    }

    //单例对象（在类装载时进行创建）
    private static SingletonExample2 instance = new SingletonExample2();

    public static SingletonExample2 getInstance(){
        return instance;
    }
}