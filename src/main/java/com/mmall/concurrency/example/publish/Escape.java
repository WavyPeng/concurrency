package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {
    private int thisCanEscape = 0;

    public Escape(){
        new InnerClass();
    }

    //内部类包含了对类隐含实例的引用
    //当对象没有被正确构造之前，就会被发布，可能存在不安全的因素在里面
    //一个导致this引用在构造期间逸出的错误，在构造函数的过程中相当于启动了一个线程
    //无论是隐式地启动，还是显式地启动，都会造成this引用的逸出
    //新线程会在构造对象完毕之前，就已经看到this对象
    //因此，要在构造函数中创建线程，则不要启动它，而应该采用专有的start()或初始化的方法来统一启动线程
    //这里可以采用工厂方法和私有构造函数来完成对象创建和监听器的注册等等，这样才可以避免不正确的创建
    //这里的目的是对象未完成构造之前，不可以将其发布
    private class InnerClass{
        public InnerClass(){
            log.info("{}",Escape.this.thisCanEscape);
        }
    }

    public static void main(String[] args){
        new Escape();
    }
}