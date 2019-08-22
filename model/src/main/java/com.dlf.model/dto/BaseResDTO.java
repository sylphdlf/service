package com.dlf.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BaseResDTO<T> implements Serializable{

    private static final long serialVersionUID = 1L;
    //参数
    private T data;
}
