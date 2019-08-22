package com.dlf.model.enums.file;

public enum FileEnums {

    STATUS_0(0, "缓存状态"),
    STATUS_1(1, "正常状态"),
    ;
    private Integer code;
    private String msg;

    FileEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
