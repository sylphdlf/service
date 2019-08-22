package com.dlf.model.dto.user;

import com.dlf.model.dto.BaseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RoleReqDTO extends BaseDTO {

    private Long id;
    //角色编号
    private String code;
    //角色名称
    private String name;

    private Long orgId;
    //备注
    private String remarks;

    private List<Long> originalIds;

    private List<Long> targetIds;
}
