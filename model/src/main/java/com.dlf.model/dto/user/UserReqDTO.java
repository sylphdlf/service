package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserReqDTO{

    private String id;
    private String userId;
    private String username;
    private String password;
    private String orgCode;

}
