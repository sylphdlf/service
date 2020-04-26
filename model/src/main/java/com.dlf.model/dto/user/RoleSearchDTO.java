package com.dlf.model.dto.user;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RoleSearchDTO extends PageDTO {

    private String name;

}
