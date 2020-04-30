package com.dlf.model.dto.message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MsgReqDTO {
    //手机
    private String mobile;
    //内容
    private String content;

    private String verifyCode;

    private String redisKey;
}
