package com.dlf.model.dto.user;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class FunDTO implements Serializable {

    private static final long serialVersionUID = 6868203583267512188L;

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
    //创建时间
    private Date createTime;
    //是否选中
    private boolean checked;

    private List<FunDTO> children;

    private Integer isDeleted;
}
