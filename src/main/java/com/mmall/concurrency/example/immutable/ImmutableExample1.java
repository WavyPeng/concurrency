package com.mmall.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * final修饰变量
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String b = "a";
    private final static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
//        a = 2;
//        b = "b";
//        map = new Maps.newHashMap();
//        以上变量不可修改，编译会出错

//        修饰引用时，final只是限制其不能指向其他对象，但对象中的值是可以修改的
        map.put(1,3);
        log.info("{}",map.get(1));
    }

    private void test(final int a){
//        a = 2;
    }
}