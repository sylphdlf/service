package com.dlf.model.dto.mq;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScoreMqPushDTO extends BaseMqPushDTO{

    private String orderId;

    private String targetUserId;

    private Integer type;
}
