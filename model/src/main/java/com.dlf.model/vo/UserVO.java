package com.dlf.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserVO implements Serializable {

    private String id;
    private String username;
    private String password;
    private Integer type;
    private String verifyCode;
    private Integer expireTime;

}
