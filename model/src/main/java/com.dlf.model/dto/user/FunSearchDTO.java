package com.dlf.model.dto.user;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FunSearchDTO extends PageDTO {
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

}
