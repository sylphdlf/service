package com.dlf.model.dto.comm;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DictReqDTO implements Serializable {

    private String dictKey;

    private String dictValue;

}
