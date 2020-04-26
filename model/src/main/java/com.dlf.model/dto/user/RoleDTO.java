package com.dlf.model.dto.user;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RoleDTO {

    private Long id;

    private String name;

    private Integer type;

    private String typeName;

    private Date createTime;

}
