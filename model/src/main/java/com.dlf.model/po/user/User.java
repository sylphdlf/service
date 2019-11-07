package com.dlf.model.po.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Data
@Entity(name = "u_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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