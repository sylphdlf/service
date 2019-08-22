package com.dlf.model.po.comm;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AreaCode {
    private String id;

    private String code;

    private String name;

    private String parentCode;

    private String remarks;

    private String createTime;

    private String createUserId;

    private String updateTime;

    private String updateUserId;

    private String isDeleted;

}