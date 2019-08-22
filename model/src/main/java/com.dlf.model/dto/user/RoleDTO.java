package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RoleDTO {

    private Long id;

    private Long orgId;

    private String orgName;

    private String code;

    private String name;

    private String remarks;

    private Date createTime;

    private boolean checked;
}
