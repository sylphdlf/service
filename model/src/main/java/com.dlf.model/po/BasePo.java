package com.dlf.model.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class BasePo {

    private Integer status;
    private String remarks;
    private Long createUserId;
    private Date createTime;
    private Long updateUserId;
    private Date updateTime;
    private String orgCode;
    private Integer isDeleted;
}
