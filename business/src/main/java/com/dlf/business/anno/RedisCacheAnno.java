package com.dlf.business.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在方法开头从缓存中获取数据，如果缓存中没有数据，则把返回值放入缓存中
 */
@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RedisCacheAnno {
    //缓存中的键
    String key() default "";
    //超时时间(秒)
    long timeout();
}
