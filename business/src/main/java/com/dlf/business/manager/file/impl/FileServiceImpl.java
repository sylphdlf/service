package com.dlf.business.manager.file.impl;

import com.dlf.business.manager.file.FileService;
import com.dlf.model.dto.GlobalResultDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public GlobalResultDTO upload(String userId, MultipartFile file) {
        return null;
    }
}
