package com.mmall.concurrency.annotations;

/**
 * 课程里用来标记【线程不安全】的类或者写法
 * Created by Wavy Peng on 2018/4/18.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NotThreadSafe {
    String value() default "";
}
