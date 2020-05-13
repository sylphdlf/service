package com.dlf.model.po.order;

import com.dlf.model.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "o_order_outer")
public class OrderOuter extends BasePo {

    private Long userId;

    private String code;

    private String platform;

    private String mobile;

    private String verifyCode;

    private Integer status;
}
