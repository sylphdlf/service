package com.dlf.business.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来描述二级菜单，获取菜单列表的时候能被扫描到
 */
@Target(ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface MenuDescriptionAnno {
    String name();//菜单名称
    String parent();//上级菜单名称
}
