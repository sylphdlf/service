package com.dlf.model.dto.file;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FileResDTO {
    private String id;
    private String file;
    private String fileName;
    private String path;
}
