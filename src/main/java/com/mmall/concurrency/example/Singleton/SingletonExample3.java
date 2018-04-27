package com.mmall.concurrency.example.Singleton;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式（线程安全的，但不推荐）
 * 单例实例在第一次使用时进行创建
 *
 * 缺点：
 * 由于使用synchronized进行同步，因此会造成不必要的性能开销
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {
    // 私有的构造函数，防止外界随意调用
    private SingletonExample3(){

    }

    //单例对象
    private static SingletonExample3 instance = null;

    //静态的工厂方法获取单例对象
    public static synchronized SingletonExample3 getInstance(){
        //使用synchronized修饰保证同一时间只有一个线程访问，因此是线程安全的
        if(instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}