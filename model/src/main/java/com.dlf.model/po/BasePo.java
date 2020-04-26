package com.dlf.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
