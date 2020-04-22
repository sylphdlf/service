package com.dlf.model.po.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@Entity
@Table(name = "u_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String mobile;

    private Integer type;

    private Integer status;

    private String remarks;

    private Long createUserId;

    private Date createTime;

    private Long updateUserId;

    private Date updateTime;

    private String orgCode;

    private Integer isDeleted;
}