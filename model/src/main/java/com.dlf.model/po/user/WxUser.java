package com.dlf.model.po.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@Table(name = "u_user_wx")
@Entity
public class WxUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String nickName;

    private String openId;

    private String remarks;

    private Long createUserId;

    private Date createTime;

    private Long updateUserId;

    private Date updateTime;

    private String orgCode;

    private Integer isDeleted;

}