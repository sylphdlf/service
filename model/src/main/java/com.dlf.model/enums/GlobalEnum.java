package com.dlf.model.enums;

public enum GlobalEnum {
    COUNT_1(1, "新增或更新的数量为1"),
    COUNT_0(0, "数量为0"),
    IS_DELETED_0(0,"正常"),
    IS_DELETED_1(1,"删除"),
    DEFAULT_VERSION_ID(1,"默认版本号为1"),
    ;

    private Integer code;
    private String msg;

    GlobalEnum(Integer code, String msg) {
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