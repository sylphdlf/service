package com.dlf.model.dto.user;

import com.dlf.model.dto.PageDTO;
import com.dlf.model.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserDTO extends BasePo {

    private Long id;

    private String username;

    private String mobile;

    private Integer type;
}
