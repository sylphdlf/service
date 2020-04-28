package com.dlf.model.dto.mq;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OrderMqPushDTO extends BaseMqPushDTO{

    private String id;

    private String orderCode;

    private String leavingLocation;

    private String leavingStr;

    private String arrivalLocation;

    private String arrivalStr;

    private Long distance;

    private Integer vehicleUseType;

    private Integer cargoType;

    private BigDecimal cargoWeight;

    private Integer weightUnit;

    private Integer status;

    private String vehicleUserId;

}
