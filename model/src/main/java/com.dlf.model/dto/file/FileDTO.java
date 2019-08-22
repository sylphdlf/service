package com.dlf.model.dto.file;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FileDTO {

    private Long id;
    private String orgName;
    private Long size;
    private Date createTime;
    private String remarks;
}
