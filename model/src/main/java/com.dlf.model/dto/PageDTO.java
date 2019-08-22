package com.dlf.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageDTO<T> {
    //每页数量
    private Integer pageSize = 10;
    //当前页数
    private Integer pageNum;
}
