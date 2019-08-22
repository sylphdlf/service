package com.dlf.model.enums.file;

public enum FileUserEnums {

    STATUS_0(0, "缓存状态"),
    STATUS_1(1, "正常状态"),
    TYPE_1(1, "投诉照片")
    ;
    private Integer code;
    private String msg;

    FileUserEnums(Integer code, String msg) {
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
