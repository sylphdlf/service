package com.dlf.model.enums.redis;

public enum RedisPrefixEnums {
    VERIFY_CODE("verify_code_", "验证码前缀"),
    SEND_INTERVAL("send_interval_","同一号码2次发送的间隔"),
    ;
    private final String code;
    private final String desc;

    RedisPrefixEnums(String code, String desc) {
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
