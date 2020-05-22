package com.dlf.model.dto.file;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileReqDTO {

    private String id;

    private String md5;

    private String suffix;

    private String userId;

    private String fileName;

    private Long orderId;

    private Long size;

}
