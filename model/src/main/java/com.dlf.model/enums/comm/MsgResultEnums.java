package com.dlf.model.enums.comm;

import com.dlf.model.enums.ICommResultEnums;

public enum MsgResultEnums implements ICommResultEnums {

    MOBILE_ILLEGAL("mobile_illegal", "请传入正确的手机号码"),
    VERIFY_CODE_ERROR("verify_code_error", "验证码无效"),
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
