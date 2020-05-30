package com.dlf.model.po.comm;

import com.dlf.model.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "s_dict")
public class SysDict extends BasePo{

    private String name;
    private String dictKey;
    private String dictValue;
    private Long parentId;
    private Integer sort;
    private Integer status;

}