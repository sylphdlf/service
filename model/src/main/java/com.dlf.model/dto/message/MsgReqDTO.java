package com.dlf.model.dto.message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MsgReqDTO {

    /**
     * 目标对象（手机、邮箱）
     */
    private String username;
    //验证码
    private String verifyCode;
    //发送时间间隔
    private Long sendInterval;
    //验证码超时时间
    private Long expireTime;
    //内容
    private String content;
    //图片验证码
    private String imgCode;

}
