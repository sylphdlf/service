package com.dlf.business.manager.file;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.file.FileReqDTO;
import com.dlf.model.dto.file.FileSearchDTO;

public interface FileService {

    GlobalResultDTO saveFromOd(FileReqDTO reqDTO);

    GlobalResultDTO save(FileReqDTO reqDTO);

    GlobalResultDTO queryPage(FileSearchDTO searchDTO);

    GlobalResultDTO queryPageForUser(FileSearchDTO searchDTO);

}
