package com.dlf.model.enums.user;

public enum RoleResultEnums {

    ROLE_REQ_NAME_NULL("role_001", "角色名称不能为空"),
    ROLE_REQ_CODE_NULL("role_002", "角色编号不能为空"),
    ROLE_REQ_ORG_NULL("role_003", "组织ID不能为空"),
    ;

    private String code;
    private String msg;

    RoleResultEnums(String code, String msg) {
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