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
@Table(name = "o_order_file")
public class OrderFile extends BasePo {

    private Long orderId;

    private String fileId;

    private Integer status;
}
