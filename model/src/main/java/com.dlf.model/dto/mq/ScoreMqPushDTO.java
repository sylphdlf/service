package com.dlf.model.dto.mq;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ScoreMqPushDTO extends BaseMqPushDTO{

    private String orderId;

    private String targetUserId;

    private Integer type;
}
