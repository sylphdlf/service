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
@Table(name = "f_file")
public class File extends BasePo {

    private String name;

    private String suffix;

    private String path;

    private Long size;

    private String md5;

    private Integer status;
}
