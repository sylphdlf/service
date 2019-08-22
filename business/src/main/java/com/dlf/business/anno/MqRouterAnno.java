package com.dlf.business.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来对标记的方法自动路由
 */
@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface MqRouterAnno {
    String topic();
    String tags();
}
