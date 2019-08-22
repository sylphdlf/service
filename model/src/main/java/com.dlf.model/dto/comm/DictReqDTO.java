package com.dlf.model.dto.comm;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DictReqDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String dictKey;

    private String dictValue;

    private Integer type;
}
