package com.dlf.model.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CommEnums {

    IS_DELETED_0(0, "正常状态"),
    IS_DELETED_1(1, "删除状态"),
    ;

    private final Integer code;
    private final String desc;
    private static Map<Integer, String> keyValueMap;


    CommEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static Map<Integer, String> getKeyValueMap(String dictKey){
        return Arrays.stream(values())
                .filter(e -> e.name().contains(dictKey.toUpperCase()))
                .collect(Collectors.toMap(CommEnums::getCode, CommEnums::getDesc));
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
