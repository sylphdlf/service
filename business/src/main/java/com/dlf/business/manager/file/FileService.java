package com.dlf.business.manager.file;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.file.FileReqDTO;

public interface FileService {

    GlobalResultDTO save(FileReqDTO reqDTO);
}
