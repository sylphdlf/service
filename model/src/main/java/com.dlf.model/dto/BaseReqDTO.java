package com.dlf.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BaseReqDTO<T> implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * {"deviceType":"03","requestTime":"20190329094622","data":"{\"username\":\"dailf\",\"password\":\"123456\"}",
     * "mac":"F1E2376AA96B8B84F14756A30258F59E","
     * id":"IC5385126301","remoteIp":"127.0.0.1","action":"/user/login"}
     */
    //每次请求附带的requestId
    private String id;
    //接口名称
    private String action;
    //接口版本
    private String version;
    //设备类型
    private String deviceType;
    //访问时间
    private String requestTime;
    //用户ID
    private String userId;

    private Integer type;
    //图片验证码校验标识
    private Integer isCheckImgCode;
    //参数
    private T data;
}
