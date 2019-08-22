package com.dlf.model.po.comm;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class File {
    private String id;

    private String name;

    private String suffix;

    private String pathRoot;

    private String pathRelative;

    private Long fileSize;

    private String md5;

    private Integer status;

    private String remarks;

    private String createTime;

    private String createUserId;

    private String updateTime;

    private String updateUserId;

    private String isDeleted;

}