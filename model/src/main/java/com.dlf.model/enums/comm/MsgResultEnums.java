package com.dlf.model.enums.comm;

public enum MsgResultEnums {

    MSG_OO1("msg_001", "请传入正确的手机号码"),
    MSG_OO2("msg_002", "验证码已失效，请重新发送"),
    MSG_OO3("msg_003", "验证码错误"),
    ;
    private String code;
    private String msg;

    MsgResultEnums(String code, String msg) {
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
