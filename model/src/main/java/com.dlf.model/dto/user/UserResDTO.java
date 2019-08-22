package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResDTO {

    private Long id;

    private String userId;

    private Integer status;

    private Integer type;

    private Integer userInfoStatus;

    private Integer companyStatus;

    private Integer vehicleStatus;

    private String remarks;

}
