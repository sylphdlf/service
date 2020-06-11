package com.dlf.model.dto.sys;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysLogSearchDTO extends PageDTO {

    private String name;
    private String content;
}
