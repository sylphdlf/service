package com.dlf.model.enums.user;

import com.dlf.model.enums.ICommEnums;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

public enum UserEnums implements ICommEnums {
    STATUS_0(0, "初始状态"),
    STATUS_1(1, "未审核"),
    STATUS_2(2, "审核成功"),
    STATUS_3(3, "审核失败"),
    STATUS_4(4, "黑名单"),

    //用户类型
    TYPE_0(0, "超级管理员"),
    TYPE_1(1, "微信小程序"),
    ;

    private final Integer code;
    private final String desc;

    private static Map<Integer, String> userTypeMap;

    UserEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getUserTypeStr(Integer userType){
        if(CollectionUtils.isEmpty(userTypeMap)){
            setUserTypeMap();
        }
        return userTypeMap.get(userType);
    }

    private static synchronized void setUserTypeMap(){
        if(CollectionUtils.isEmpty(userTypeMap)){
            userTypeMap = new HashMap<>();
            userTypeMap.put(TYPE_1.code, TYPE_1.desc);
            userTypeMap.put(TYPE_0.code, TYPE_0.desc);
        }
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}