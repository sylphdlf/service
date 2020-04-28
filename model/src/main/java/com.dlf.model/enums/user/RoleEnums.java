package com.dlf.model.enums.user;

import com.dlf.model.enums.ICommEnums;

public enum RoleEnums implements ICommEnums {

    //角色类型
    TYPE_0(0, "普通用户"),
    TYPE_1(1, "超级管理员"),
    TYPE_2(2, "微信小程序"),
    ;

    private final Integer code;
    private final String desc;

    RoleEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}