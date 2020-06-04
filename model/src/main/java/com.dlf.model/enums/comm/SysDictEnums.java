package com.dlf.model.enums.comm;

public enum SysDictEnums {

    MSG_INTERVAL("msg_interval", "短信发送间隔(秒)"),
    MSG_EXPIRE("msg_expire", "短信发送超时时间(秒)"),

    status_0("0", "")
    ;

    private final String code;
    private final String desc;

    SysDictEnums(String code, String desc) {
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
