package com.dlf.model.dto.mq;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseMqPushDTO {

    private String userId;

    private Integer userType;

    private String username;
}
