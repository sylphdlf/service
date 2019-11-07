package com.dlf.model.enums.user;

import java.util.Enumeration;

public enum UserResultEnums implements Enumeration {

    USER_NOT_EXISTED("user_001","用户不存在"),
    USER_EXISTED("user_002","该用户已存在"),
//    TOKEN_ERROR("user_001","TOKEN_ERROR"),
//    PERMISSION_DENIED("user_002","没有权限"),
//    USERNAME_OR_PASSWORD_ERROR("user_003","用户名或密码错误"),
//    REGISTER_FAIL("user_004","注册失败"),
//    USERNAME_NULL("user_005","用户名不能为空"),
//    PASSWORD_NULL("user_006","密码不能为空"),
//    PASSWORD_REPEAT_NULL("user_007","请再次输入密码"),
//    PASSWORD_REPEAT_NOT_MATCH("user_008","2次输入的密码不一致"),
//    USER_NULL("user_009","没有此用户，请确认用户名是否输入正确"),
//
//    USER_STATUS_ILLEGAL("user_011","用户状态不对"),
//    PASSWORD_OR_VERIFY_NULL("user_013","用户名或验证码错误"),
//    USER_MATCH_ERROR("user_014","无法匹配用户"),
//    USER_UPDATE_FAIL("user_015", "用户更新失败"),
//    PASSWORD_ILLEGAL("user_016", "密码格式错误"),
//    ID_CARD_NO_ILLEGAL("user_017", "身份证格式错误"),
//    USER_ID_NULL("user_018", "userId不能为空"),
//    OPERATING_FREQUENCY("user_019", "操作频繁"),
//    USER_TYPE_ILLEGAL("user_020", "用户类型不对"),
//    USER_BLACKLIST("user_021", "已被列入黑名单"),
    ;

    private String code;
    private String msg;

    UserResultEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public boolean hasMoreElements() {
        return false;
    }

    @Override
    public Object nextElement() {
        return null;
    }
}