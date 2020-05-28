package com.dlf.model.po.user;

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
@Table(name = "u_access_log")
public class AccessLog extends BasePo {

    private Long userId;
    private String ipAddr;
    private String session_id;
    private String url;
    private Integer accessCount;
}
