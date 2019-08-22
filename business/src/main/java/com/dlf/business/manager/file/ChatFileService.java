//package com.dlf.business.manager.file;
//
//import com.dlf.business.exception.MyException;
//import com.dlf.model.dto.GlobalResultDTO;
//import com.dlf.model.dto.file.FileResDTO;
//import org.springframework.web.multipart.MultipartFile;
//
//public interface ChatFileService {
//
//    /**
//     * 聊天文件上传
//     * @param userId
//     * @param file
//     * @return
//     * @throws MyException
//     */
//    GlobalResultDTO uploadFile(String userId, String targetUserId, MultipartFile file) throws MyException;
//
//    /**
//     * 获取文件路径
//     * @param id
//     * @param userId
//     * @param type
//     * @return
//     */
//    GlobalResultDTO<FileResDTO> getFilePath(String id, String userId, String targetUserId, Integer type);
//}
