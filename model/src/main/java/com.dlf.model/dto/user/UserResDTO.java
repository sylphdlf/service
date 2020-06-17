package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserResDTO implements Serializable {

    private static final long serialVersionUID = -4157124269631470893L;
    private Long id;

    private String username;

    private String password;

    private Integer isAdmin;

    private String lastIp;

    private Date updateTime;
}
