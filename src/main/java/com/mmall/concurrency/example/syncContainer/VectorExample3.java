package com.mmall.concurrency.example.syncContainer;

import com.mmall.concurrency.annotations.NotThreadSafe;

import java.util.Iterator;
import java.util.Vector;

/**
 *
 * Created by WavyPeng on 2018/4/28.
 */

public class VectorExample3 {

    //采用foreach遍历
    //运行抛出java.util.ConcurrentModificationException异常
    //对一个集合进行遍历的同时又对其进行增删操作，导致modCount和expectedModCount
    //两个值不一致，从而触发抛出异常
    private static void test1(Vector<Integer> v1){
        for(Integer i : v1){
            if(i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    //采用迭代器的方式
    //运行抛出java.util.ConcurrentModificationException异常
    private static void test2(Vector<Integer> v1){
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            if(i.equals(3)){
                v1.remove(i);
            }
        }
    }

    //使用正常的for循环
    //运行success
    private static void test3(Vector<Integer> v1){
        for(int i=0;i<v1.size();i++){
            if(v1.get(i).equals(3)){
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test3(vector);
    }
}
