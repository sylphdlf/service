package com.dlf.model.po.sys;

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
@Table(name = "s_sys_log")
public class SysLog extends BasePo{

    private String name;
    private String content;
    private Integer priority;

}