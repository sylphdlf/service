package com.dlf.model.po.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class User {
    private Long id;

    private String username;

    private String password;

    private String mobile;

    private Integer status;

    private String remarks;

    private Long createUserId;

    private Date createTime;

    private Long updateUserId;

    private Date updateTime;

    private String orgCode;

    private Integer isDeleted;

}