package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WxUserReqDTO {

    private String openId;

    private String mobile;

    private String verifyCode;

    private String remarks;
}
