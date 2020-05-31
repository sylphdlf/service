package com.dlf.model.dto.comm;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class DictSearchDTO extends PageDTO {

    private Long id;

    private Long parentId;
}
