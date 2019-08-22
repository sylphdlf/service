package com.dlf.model.enums.mq;

public enum MqTopicEnums {

    ;

    private String code;
    private String desc;

    MqTopicEnums(String code, String desc) {
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
