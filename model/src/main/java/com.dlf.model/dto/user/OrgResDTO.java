package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class OrgResDTO {

    private Long id;

    private String name;

    private String code;

    private String parentCode;

    private Integer level;

    private String remarks;

    private Date createTime;
}
