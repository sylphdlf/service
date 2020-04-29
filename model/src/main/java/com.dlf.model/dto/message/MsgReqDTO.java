package com.dlf.model.dto.message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MsgReqDTO {
    //手机
    private String mobile;
    //验证码
    private String verifyCode;
    //内容
    private String content;
}
