package com.dlf.model.enums.user;

public enum FunctionEnums {

    function_type_1(1,"菜单"),
    function_type_2(2,"按钮"),
    function_type_3(3,"资源"),
    function_level_1(1,"一级菜单"),
    function_level_2(2,"二级菜单"),
    ;

    private Integer code;
    private String msg;

    FunctionEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}