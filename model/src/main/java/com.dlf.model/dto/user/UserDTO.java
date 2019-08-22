package com.dlf.model.dto.user;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserDTO{

    private String id;

    private String username;

    private String realName;

    private String idCardNo;

    private String companyName;

    private String locationCode;

    private Integer ownerStatus;
}
