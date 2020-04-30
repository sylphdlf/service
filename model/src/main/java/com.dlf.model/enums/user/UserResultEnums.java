package com.dlf.model.enums.user;


import com.dlf.model.enums.ICommResultEnums;

public enum UserResultEnums implements ICommResultEnums {

    USER_NOT_EXISTED("user_001","用户不存在"),
    USER_EXISTED("user_002","该用户已存在"),
    VERIFY_CODE_ERROR("user_003","验证码无效"),
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

}