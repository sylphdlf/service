package com.dlf.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseReqDTO{

    private Long userId;
    private String orgCode;

    private Integer pageNum;
    private Integer pageSize = 15;

}
