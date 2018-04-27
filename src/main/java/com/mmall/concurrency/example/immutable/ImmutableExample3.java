package com.mmall.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class ImmutableExample3 {
    //声明Guava immutable集合对象
    private final static ImmutableList list = ImmutableList.of(1,2,3);
    private final static ImmutableSet set = ImmutableSet.copyOf(list);
    private final static ImmutableMap<Integer,Integer> map = ImmutableMap.of(1,2,3,4);
    private final static ImmutableMap<Integer,Integer> map2 = ImmutableMap.<Integer,Integer>builder()
            .put(1,2).put(3,4).build();

    public static void main(String[] args) {
//        list.add(4); // 此方法不建议被使用
//        set.add(4);  // 此方法不建议被使用
//        map.put(1,4);
//        map2.put(1,4);
        System.out.println(map2.get(3));
    }
}