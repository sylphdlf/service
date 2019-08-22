package com.dlf.model.enums.file;

public enum FileChatEnums {

    STATUS_0(0, "缓存状态"),
    STATUS_1(1, "正常状态"),
    IS_READ_0(0, "是否已读：未读"),
    IS_READ_1(1, "是否已读：已读"),
    ;
    private Integer code;
    private String msg;

    FileChatEnums(Integer code, String msg) {
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
