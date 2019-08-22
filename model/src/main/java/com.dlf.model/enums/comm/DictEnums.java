package com.dlf.model.enums.comm;

public enum DictEnums {

    STATUS_1(1, "启动"),
    STATUS_2(2, "停用"),
    TYPE_1(1, "页面字典")
    ;

    private Integer code;
    private String desc;

    DictEnums(Integer code, String desc) {
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