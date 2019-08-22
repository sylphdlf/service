//package com.dlf.business.manager.file.impl;
//
//import com.dlf.business.exception.MyException;
//import com.dlf.business.manager.file.ChatFileService;
//import com.dlf.common.utils.CodeGenerateUtils;
//import com.dlf.common.utils.Md5Utils;
//import com.dlf.model.dto.GlobalResultDTO;
//import com.dlf.model.dto.file.FileResDTO;
//import com.dlf.model.enums.GlobalEnum;
//import com.dlf.model.enums.file.FileChatEnums;
//import com.dlf.model.enums.file.FileEnums;
//import com.dlf.model.enums.file.FileResultEnums;
////import com.dlf.com.dlf.model.enums.file.FileUserEnums;
//import com.dlf.model.mapper.comm.FileChatMapper2;
//import com.dlf.model.mapper.comm.FileMapper2;
//import com.dlf.model.mapper.comm.FileUserMapper2;
//import com.dlf.model.po.comm.File;
//import com.dlf.model.po.comm.FileChat;
////import net.coobird.thumbnailator.Thumbnails;
//import org.apache.commons.collections.CollectionUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.Resource;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.security.NoSuchAlgorithmException;
//import java.util.*;
//
//@Service
//public class ChatFileServiceImpl implements ChatFileService {
//
//    @Resource
//    FileMapper2 fileMapper;
//    @Resource
//    FileUserMapper2 fileUserMapper;
//    @Resource
//    FileChatMapper2 fileChatMapper;
//
//    @Value("${comm.upload.root}")
//    private String fileUploadRoot;
//
//    private String defaultFilePathPrefix = "mat/file/showImg?id=";
//    @Override
//    @Transactional
//    public GlobalResultDTO uploadFile(String userId, String targetUserId, MultipartFile file) throws MyException {
//        //根据MD5查询文件重复,并且返回文件类型
//        File fileResult = this.checkFileAndInsert(file);
//        //绑定文件和用户
//        FileChat fileChatResult = this.insertOrBindUser(fileResult, userId, targetUserId, file.getOriginalFilename());
//        //返回文件下载路径和名称
//        FileResDTO fileResDTO = new FileResDTO();
//        fileResDTO.setFileName(fileChatResult.getOrgName());
//        fileResDTO.setPath(defaultFilePathPrefix + fileResult.getId());
//        return GlobalResultDTO.SUCCESS(fileResDTO);
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public GlobalResultDTO<FileResDTO> getFilePath(String id, String userId, String targetUserId, Integer type) {
//        FileChat fileChat = new FileChat();
//        fileChat.setFileId(id);
//        fileChat.setUserId(userId);
//        fileChat.setTargetUserId(targetUserId);
//        List<FileChat> fileChatList = fileChatMapper.queryListByParams(fileChat);
//        if(CollectionUtils.isEmpty(fileChatList)){
//            throw new MyException(FileResultEnums.FILE_NOT_EXIST.getCode(), FileResultEnums.FILE_NOT_EXIST.getMsg());
//        }
//        File file = fileMapper.selectByPrimaryKey(id);
//        String filePath = fileUploadRoot + file.getPathRelative() + file.getName() + (type==0?"_min":"") + "." + file.getSuffix();
//        FileResDTO fileResDTO = new FileResDTO();
//        fileResDTO.setPath(filePath);
//        fileResDTO.setFileName(fileChatList.get(0).getOrgName());
//        return GlobalResultDTO.SUCCESS(fileResDTO);
//    }
//
//    /**
//     * 查询文件是否
//     * @param file
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    private File checkFileAndInsert(MultipartFile file){
//        //文件MD5编码
//        String md5Str = null;
//        try {
//            md5Str = Md5Utils.md5Encoding(file);
//        } catch (IOException | NoSuchAlgorithmException e) {
//            throw new MyException(FileResultEnums.MD5_ENCODER_FAIL.getCode(), FileResultEnums.MD5_ENCODER_FAIL.getMsg());
//        }
//        //查询是否有重复的记录
//        File fileSearch = new File();
//        fileSearch.setMd5(md5Str);
//        List<File> fileList = fileMapper.queryListByParams(fileSearch);
//        if(CollectionUtils.isEmpty(fileList)){
//            return this.insertFile(md5Str, file);
//        }else{
//            return fileList.get(0);
//        }
//    }
//    @SuppressWarnings("unchecked")
//    private FileChat insertOrBindUser(File file, String userId, String targetUserId, String orgFileName){
//        //判断是否有文件-用户关联关系
//        FileChat fileChat = new FileChat();
//        fileChat.setFileId(file.getId());
//        fileChat.setUserId(userId);
//        fileChat.setTargetUserId(targetUserId);
//
//        List<FileChat> fileChatList = fileChatMapper.queryListByParams(fileChat);
//        //创建文件用户关联
//        if(CollectionUtils.isEmpty(fileChatList)){
//            fileChat.setOrgName(orgFileName);
//            fileChat.setStatus(FileChatEnums.STATUS_1.getCode());
//            fileChat.setIsRead(FileChatEnums.IS_READ_0.getCode());
//            int count = fileChatMapper.insert(fileChat);
//            if(count != 1){
//                throw new MyException(FileResultEnums.FILE_SAVE_FAIL.getCode(), FileResultEnums.FILE_SAVE_FAIL.getMsg());
//            }
//            return fileChat;
//        }else{
//            return fileChatList.get(0);
//        }
//    }
//
//    private File insertFile(String md5Str, MultipartFile file) throws  MyException {
//        //原始文件名
//        String originalFileName = file.getOriginalFilename();
//        File fileInsert = new File();
//        //自动生成的文件名, FC=chatFile聊天文件
//        String fakeName = CodeGenerateUtils.codeGenerateByPrefix("FC");
//        //FIXME 可能没有文件后缀
//        String suffix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length());
//        //文件夹路径
//        String folderPath = fileUploadRoot;
//        String filePathRelative = java.io.File.separator;
//        fileInsert.setName(fakeName);
//        fileInsert.setSuffix(suffix);
//        fileInsert.setMd5(md5Str);
//        fileInsert.setPathRoot(folderPath);
//        fileInsert.setPathRelative(filePathRelative);
//        fileInsert.setFileSize(file.getSize());
//        fileInsert.setStatus(FileEnums.STATUS_0.getCode());
//        //创建文件夹
//        java.io.File folder = new java.io.File(folderPath);
//        java.io.File thisFile = null;
//        try {
//            if (!folder.exists()) {
//                boolean mkdirs = folder.mkdirs();
//            }
//            thisFile = new java.io.File(folderPath + filePathRelative + fakeName + "." + suffix);
//            file.transferTo(thisFile);
//            //压缩，文件名后+_min
//            this.imageCompress(folderPath, filePathRelative, fakeName, suffix, file.getSize());
//        } catch (IOException e) {
//            throw new MyException(FileResultEnums.FILE_SAVE_FAIL.getCode(), FileResultEnums.FILE_SAVE_FAIL.getMsg());
//        }
//        if (fileMapper.insert(fileInsert) != GlobalEnum.COUNT_1.getCode()) {
//            //删除文件
//            boolean delete = thisFile.delete();
//            throw new MyException(FileResultEnums.FILE_INSERT_FAIL.getCode(), FileResultEnums.FILE_INSERT_FAIL.getMsg());
//        }
//        return fileInsert;
//    }
//
//    private void imageCompress(String folderPath, String filePathRelative, String fakeName, String suffix, Long fileSize) throws IOException {
//        OutputStream os = new FileOutputStream(folderPath + filePathRelative + fakeName + "_min" + "." + suffix);
//        //Thumbnails.of(folderPath + filePathRelative + fakeName + "." + suffix).scale(1f).outputQuality(0.1f).toOutputStream(os);
//    }
//}
