package com.dlf.model.enums;

public enum GlobalResultEnum implements ICommResultEnums{

    SUCCESS("0","成功"),
    FAIL("-1","失败"),
    LOG_OUT("-2","未登录"),
    CACHE("1","get data from cache"),
    MISSING_PARAMETER("-3", "丢失参数"),
    UNIQUE_ERROR("-4", "名称已经被使用了"),
    ;

    private String code;
    private String msg;

    GlobalResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}