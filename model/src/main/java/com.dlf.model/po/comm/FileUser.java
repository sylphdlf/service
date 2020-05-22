package com.dlf.model.po.comm;

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
@Table(name = "f_file_user")
public class FileUser extends BasePo {

    private Long fileId;

    private String name;

    private Long userId;

    private Integer status;
}
