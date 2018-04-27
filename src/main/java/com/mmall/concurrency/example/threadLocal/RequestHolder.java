package com.mmall.concurrency.example.threadLocal;

public class RequestHolder {
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    /**
     * 请求在进入到后台服务器，但是却没有进行实际处理的时候，
     * 调用add方法，将相关信息写进去
     * 这时就会使用filter，filter会拦住对应的url
     * 当前台访问此url时，在filter中把相关的信息写到ThreadLocal中去
     * 这样在此url在实际被处理时就可以在ThreadLocal中进行寻找
     * @param id
     */
    public static void add(Long id){
        requestHolder.set(id);
    }


    public static Long getId(){
        return requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }


}