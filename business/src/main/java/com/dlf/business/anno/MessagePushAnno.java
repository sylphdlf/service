package com.dlf.business.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记需要推送消息的方法
 */
@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface MessagePushAnno {

    METHOD method();

    enum METHOD{
        ORDER_DEFAULT,
        ORDER_AGENT,//承运商
    }
}
