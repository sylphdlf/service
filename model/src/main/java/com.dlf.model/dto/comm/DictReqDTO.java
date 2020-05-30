package com.dlf.model.dto.comm;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DictReqDTO implements Serializable {

    private static final long serialVersionUID = 3405512261283868487L;
    private Long id;

    private String name;

    private Long parentId;

    private String dictKey;

    private String dictValue;

}
