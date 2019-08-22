package com.dlf.model.po.comm;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileUser {
    private String id;

    private String fileId;

    private String orgName;

    private String userId;

    private Integer status;

    private Integer type;

    private String remarks;

    private String createTime;

    private String createUserId;

    private String updateTime;

    private String updateUserId;

    private String isDeleted;

}