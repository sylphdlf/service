package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleReqDTO {

    private Long id;

    private String name;

    private Integer type;

}
