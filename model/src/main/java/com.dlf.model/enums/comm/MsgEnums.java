package com.dlf.model.enums.comm;

public enum MsgEnums {

    STATUS_1(1, "未读"),
    STATUS_2(2, "已读"),
    ;

    private Integer code;
    private String desc;

    MsgEnums(Integer code, String desc) {
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