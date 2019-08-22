package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FunReqDTO {

    private Long id;
    //权限名称
    private String name;
    //权限编号
    private String code;
    //权限父编号
    private String parentCode;
    //权限类型
    private Integer type;
    //权限地址
    private String path;
    //备注
    private String remarks;
    //菜单层级
    private Integer level;
    //排序
    private Integer sortValue;
    //角色id
    private Long roleId;

    private List<FunReqDTO> children;
    //是否选中
    private boolean checked;
}
