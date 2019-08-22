package com.dlf.model.dto.message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MsgDTO {
    //验证码
    private String verifyCode;
    //超时时间(秒)
    private Long expireTime;

    public MsgDTO(Long expireTime) {
        this.expireTime = expireTime;
    }
}
