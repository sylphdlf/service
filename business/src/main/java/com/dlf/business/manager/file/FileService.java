package com.dlf.business.manager.file;

import com.dlf.model.dto.GlobalResultDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    GlobalResultDTO upload(String userId, MultipartFile file);

}
