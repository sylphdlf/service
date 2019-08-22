package com.dlf.model.po.comm;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Dictionary {
    private String id;

    private String name;

    private String dictKey;

    private String dictValue;

    private Integer type;

    private Integer status;

    private String remarks;

    private String createTime;

    private String createUserId;

    private String updateTime;

    private String updateUserId;

    private String isDeleted;

}