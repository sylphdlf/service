package com.dlf.model.enums.order;

import com.dlf.model.enums.ICommResultEnums;

public enum OrderResultEnums implements ICommResultEnums {

    ORDER_NULL("order_001", "订单不存在"),

    ;

    private final String code;
    private final String msg;

    OrderResultEnums(String code, String msg) {
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