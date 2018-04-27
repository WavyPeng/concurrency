package com.mmall.concurrency.example.Singleton;

import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式 ——> 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 */
@ThreadSafe
public class SingletonExample5 {
    // 私有的构造函数，防止外界随意调用
    private SingletonExample5(){

    }

    //单例对象 volatile + 双重检测机制 -> 禁止指令重排
    //因为instance = new SingletonExample5()涉及volatile的写操作
    //volatile写会禁止和volatile读和volatile写进行指令重排

    private volatile static SingletonExample5 instance = null;


    //造成线程不安全的原因：
    //在执行instance = new SingletonExample4()时，会执行如下操作：
    //1、memory = allocate() 分配对象的内存空间
    //2、ctorInstance() 初始化对象
    //3、instance = memoty 设置instance指向刚分配的内存

    //由于JVM和CPU优化，可能发生指令重排，如下所示：

    //1、memory = allocate() 分配对象的内存空间
    //3、instance = memoty 设置instance指向刚分配的内存
    //2、ctorInstance() 初始化对象

    //假如线程A执行到instance = new SingletonExample4()这一步
    //而线程B执行到if语句
    //由于指令重排，线程A可能才执行了3、instance = memoty，而没有执行2
    //这时，B刚好检测到instance!=null，而直接返回instance
    //但是由于这时的instance还没有来得及被A线程执行初始化操作，因此B线程在执行后续操作时可能会出错。

    //解决方案：通过volatile限制指令重排

    //静态的工厂方法获取单例对象
    public static SingletonExample5 getInstance(){
        if(instance == null){  //双重检测机制
            synchronized(SingletonExample5.class) {  //同步锁
                if(instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}