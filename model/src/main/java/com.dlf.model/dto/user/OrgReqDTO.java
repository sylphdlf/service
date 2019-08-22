package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrgReqDTO {

    private Long id;

    private String name;

    private String code;

    private String parentCode;

    private Integer level;

    private String remarks;

    //绑定角色
    //原始角色
    private List<Long> originalIds;
    //修改后的角色
    private List<Long> changedIds;
    //待新增的角色ID
    private List<Long> toAddIds;
    //待删除的角色ID
    private List<Long> toDelIds;
    //创建时间
    private Date createTime;

    private Integer isDeleted;
}
