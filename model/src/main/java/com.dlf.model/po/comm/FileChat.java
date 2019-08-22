package com.dlf.model.po.comm;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileChat {
    private String id;

    private String fileId;

    private String orgName;

    private String userId;

    private String targetUserId;

    private Integer isRead;

    private Integer status;

    private String remarks;

    private String createTime;

    private String createUserId;

    private String updateTime;

    private String updateUserId;

    private String isDeleted;

}