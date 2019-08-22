package com.dlf.model.enums.comm;

public enum ImgCodeResultEnums {

    IMG_CODE_EXPIRED("img_code_001", "图片验证码已过期"),
    IMG_CODE_ERROR("img_code_002", "图片验证码错误"),
    ;
    private String code;
    private String msg;

    ImgCodeResultEnums(String code, String msg) {
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
