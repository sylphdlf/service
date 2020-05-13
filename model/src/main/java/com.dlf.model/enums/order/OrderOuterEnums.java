package com.dlf.model.enums.order;

public enum OrderOuterEnums {

    STATUS_0(0, "初始状态"),
    STATUS_1(1, "用户已查看"),
    ;

    private final Integer code;
    private final String desc;


    OrderOuterEnums(Integer code, String desc) {
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
