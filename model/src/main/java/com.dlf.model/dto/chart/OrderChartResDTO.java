package com.dlf.model.dto.chart;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OrderChartResDTO implements Serializable {

    private String days;

    private Integer count;
}
