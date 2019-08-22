package com.dlf.model.dto.file;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileUserReqDTO {

    private String fileId;
    private String userId;
}
