package com.dlf.model.enums;

public enum CommEnums {

    IS_DELETED_0("0", "正常状态"),
    IS_DELETED_1("1", "删除状态"),
    ;

    private String code;
    private String desc;

    CommEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
