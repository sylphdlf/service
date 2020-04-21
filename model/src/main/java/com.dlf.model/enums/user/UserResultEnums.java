package com.dlf.model.enums.user;

import java.util.Enumeration;

public enum UserResultEnums implements Enumeration {

    USER_NOT_EXISTED("user_001","用户不存在"),
    USER_EXISTED("user_002","该用户已存在"),
    ;

    private final String code;
    private final String msg;

    UserResultEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public boolean hasMoreElements() {
        return false;
    }

    @Override
    public Object nextElement() {
        return null;
    }
}