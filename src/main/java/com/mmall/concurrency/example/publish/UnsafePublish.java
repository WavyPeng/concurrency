package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class UnsafePublish {
    private String[] states = {"a","b","c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args){
        //通过创建对象发布了一个实例，有了实例之后，可以通过提供的public方法得到其私有域的引用
        //这样就可以在其他线程中修改私有域的值，这样就无法确定states值是否是正确的
        //这种方式是线程不安全的
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
        //修改UnsafePublish的私有变量
        unsafePublish.getStates()[0] = "d";
        log.info("{}",Arrays.toString(unsafePublish.getStates()));
    }
}