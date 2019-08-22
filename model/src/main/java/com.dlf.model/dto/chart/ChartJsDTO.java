package com.dlf.model.dto.chart;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ChartJsDTO implements Serializable {

    private static final long serialVersionUID = -1061214999953807864L;

    private List<String> label;
    private List<String> data;
    private List<String> data2;
    private List<String> data3;
    private List<String> data4;
}
