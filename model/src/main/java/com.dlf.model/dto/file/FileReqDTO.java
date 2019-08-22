package com.dlf.model.dto.file;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileReqDTO {

    private String id;

    private String userId;

    private String file;

    private String fileName;

}
