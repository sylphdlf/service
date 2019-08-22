package com.dlf.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class BaseDTO {

    //调用ip
    private String remoteIp;
    //mac
    private String mac;
    //接口地址
    private String action;
    //接口版本
    private String version;
    //设备类型
    private String deviceType;
    //访问时间
    private String requestTime;

}
