package com.dlf.model.enums.comm;

import java.util.Enumeration;

public enum DictResultEnums {

    params_null("dict_001","请传入参数"),
    ;

    private String code;
    private String msg;

    DictResultEnums(String code, String msg) {
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