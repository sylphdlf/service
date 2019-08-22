package com.dlf.model.enums.user;

public enum OrgResultEnums {

    ORG_TREE_EMPTY("org_001", "没有节点数据"),
    ;

    private String code;
    private String msg;

    OrgResultEnums(String code, String msg) {
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