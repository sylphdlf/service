package com.dlf.model.dto.user;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleSearchDTO extends PageDTO {
    //角色编号
    private String code;
    //角色名称
    private String name;
    //组织id
    private Long orgId;
}
