package com.dlf.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageDTO {
    //每页数量
    private Integer pageSize = 10;
    //当前页数
    private Integer pageIndex;
}
