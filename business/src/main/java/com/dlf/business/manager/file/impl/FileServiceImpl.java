//package com.dlf.business.manager.file.impl;
//
//import com.dlf.business.anno.ValidateAnno;
//import com.dlf.business.exception.MyException;
//import com.dlf.business.manager.file.FileService;
//import com.dlf.common.utils.Base64Utils;
//import com.dlf.common.utils.CodeGenerateUtils;
//import com.dlf.common.utils.Md5Utils;
//import com.dlf.common.utils.user.UserUtils;
//import com.dlf.model.dto.GlobalResultDTO;
//import com.dlf.model.dto.file.FileReqDTO;
//import com.dlf.model.dto.file.FileResDTO;
//import com.dlf.model.dto.file.FileUserReqDTO;
//import com.dlf.model.enums.GlobalEnum;
//import com.dlf.model.enums.GlobalResultEnum;
//import com.dlf.model.enums.file.FileEnums;
//import com.dlf.model.enums.file.FileResultEnums;
//import com.dlf.model.enums.file.FileUserEnums;
//import com.dlf.model.mapper.comm.FileMapper2;
//import com.dlf.model.mapper.comm.FileUserMapper2;
//import com.dlf.model.po.comm.File;
//import com.dlf.model.po.comm.FileUser;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class FileServiceImpl implements FileService {
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Resource
//    FileMapper2 fileMapper;
//    @Resource
//    FileUserMapper2 fileUserMapper;
//    @Value("${comm.upload.root}")
//    private String fileUploadRoot;
//
//    private static final String MD5_STR = "md5Str";
//    private static final String FILE_BYTES = "fileBytes";
//    private static final String SUFFIX = "suffix";
//
//    @Override
//    public GlobalResultDTO updateFileStatus(FileReqDTO reqDTO) {
//        File file = fileMapper.selectByPrimaryKey(reqDTO.getId());
//        file.setStatus(FileEnums.STATUS_1.getCode());
//        if(fileMapper.updateByPrimaryKey(file) == GlobalEnum.COUNT_1.getCode())return GlobalResultDTO.SUCCESS();
//        return GlobalResultDTO.FAIL();
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public GlobalResultDTO updateUserFileStatus(FileUserReqDTO reqDTO) {
//        FileUser fileUser = new FileUser();
//        fileUser.setFileId(reqDTO.getFileId());
//        fileUser.setUserId(reqDTO.getUserId());
//        List<FileUser> fileUserList = fileUserMapper.queryListByParams(fileUser);
//        fileUser = fileUserList.get(0);
//        fileUser.setStatus(FileUserEnums.STATUS_1.getCode());
//        if(fileUserMapper.updateByPrimaryKey(fileUser) == GlobalEnum.COUNT_1.getCode()) return GlobalResultDTO.SUCCESS();
//        return GlobalResultDTO.FAIL();
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    @ValidateAnno(names = {"file", "fileName"})
//    public GlobalResultDTO uploadImg(FileReqDTO reqDTO) throws MyException{
//        //md5加密文件
//        Map<String, Object> fileMap = this.Md5EncryptFile(reqDTO.getFile());
//        //查询是否有重复的记录
//        File fileSearch = new File();
//        fileSearch.setMd5((String)fileMap.get(MD5_STR));
//        List<File> fileList = fileMapper.queryListByParams(fileSearch);
//        if(CollectionUtils.isEmpty(fileList)){
//            // 新增服务器文件
//            GlobalResultDTO insertResult = this.insertServerFile((String) fileMap.get("suffix"), (String) fileMap.get(MD5_STR), (byte[]) fileMap.get(FILE_BYTES));
//            return this.queryFileUser(((File)insertResult.getData()).getId(), reqDTO.getFileName());
//        }else{
//            return this.queryFileUser(fileList.get(0).getId(), reqDTO.getFileName());
//        }
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    @ValidateAnno(names = {"id"})
//    public GlobalResultDTO getImage(FileReqDTO reqDTO) throws MyException{
//        File file = fileMapper.selectByPrimaryKey(reqDTO.getId());
//        String path = fileUploadRoot + java.io.File.separator + file.getName() + "." + file.getSuffix();
//        String base64 = Base64Utils.fileToBase64(path);
//        if(StringUtils.isBlank(base64)){
//            return GlobalResultDTO.FAIL(FileResultEnums.FILE_GET_FAIL.getCode(), FileResultEnums.FILE_GET_FAIL.getMsg());
//        }
//        String prefix = "data:image/template;base64,";
//        prefix = prefix.replace("template", file.getSuffix());
//        base64 = prefix + base64;
//        return new GlobalResultDTO(base64);
//    }
//
//    /**
//     * 删除不关联其他用户的文件
//     * @param searchDTO
//     * @return
//     */
//    @Override
//    @SuppressWarnings("unchecked")
//    public GlobalResultDTO deleteFileUser(FileUserReqDTO searchDTO) throws MyException{
//        FileUser fileUser = new FileUser();
//        fileUser.setFileId(searchDTO.getFileId());
//        List<FileUser> fileUserList = fileUserMapper.queryListByParams(fileUser);
//        if (CollectionUtils.isEmpty(fileUserList)) {
//            throw new MyException(GlobalResultEnum.NULL_VALUE.getCode(), GlobalResultEnum.NULL_VALUE.getMsg());
//        }
//        for(FileUser thisFileUser: fileUserList){
//            //判断当前用户
//            if(thisFileUser.getUserId().equals(UserUtils.getUserLocal().getId())){
//                //只有一条数据且删除成功
//                if(fileUserList.size() == GlobalEnum.COUNT_1.getCode()
//                        && fileMapper.deleteByPrimaryKey(thisFileUser.getId()) == GlobalEnum.COUNT_1.getCode()){
//                    return GlobalResultDTO.SUCCESS();
//                }
//            }
//        }
//        return GlobalResultDTO.FAIL();
//    }
//
//    /**
//     * 删除多余文件
//     * @param reqDTO
//     * @return
//     */
//    @Override
//    public GlobalResultDTO deleteFile(FileUserReqDTO reqDTO) throws MyException{
//        // 删除文件表数据
//        File file = fileMapper.selectByPrimaryKey(reqDTO.getFileId());
//        if (file == null) {
//            throw new MyException(GlobalResultEnum.NULL_VALUE.getCode(), GlobalResultEnum.NULL_VALUE.getMsg());
//        }
//        if (fileMapper.deleteByPrimaryKey(file.getId()) != GlobalEnum.COUNT_1.getCode()) {
//            throw new MyException(GlobalResultEnum.FAIL.getCode(), GlobalResultEnum.FAIL.getMsg());
//        }
//        // 组装文件路径
//        String path = file.getPathRoot() + file.getPathRelative() + file.getName() + "." + file.getSuffix();
//        java.io.File fileLocal = new java.io.File(path);
//        if (fileLocal.exists()) {
//            // 删除服务器文件
//            boolean flag = fileLocal.delete();
//            if (!flag) {
//                throw new MyException(GlobalResultEnum.FAIL.getCode(), GlobalResultEnum.FAIL.getMsg());
//            }else{
//                return GlobalResultDTO.SUCCESS();
//            }
//        } else {
//            throw new MyException(FileResultEnums.FILE_NOT_EXIST.getCode(), FileResultEnums.FILE_NOT_EXIST.getMsg());
//        }
//    }
//
//    @Override
//    public GlobalResultDTO getAppImage(FileReqDTO reqDTO) {
//        File file = fileMapper.selectByPrimaryKey(reqDTO.getId());
//        String path = fileUploadRoot + java.io.File.separator + file.getName() + "." + file.getSuffix();
//        String base64 = Base64Utils.fileToBase64(path);
//        if(StringUtils.isBlank(base64)){
//            return GlobalResultDTO.FAIL(FileResultEnums.FILE_GET_FAIL.getCode(), FileResultEnums.FILE_GET_FAIL.getMsg());
//        }
//        String prefix = "data:image/template;base64,";
//        prefix = prefix.replace("template", file.getSuffix());
//        base64 = prefix + base64;
//        //这边单独弄一个给App用
//        FileResDTO fileResDTO = new FileResDTO();
//        fileResDTO.setFile(base64);
//        return new GlobalResultDTO(fileResDTO);
//    }
//
//    /**
//     * 查询文件和用户的关联关系
//     * @param fileId
//     * @param fileName
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    private GlobalResultDTO queryFileUser(String fileId, String fileName) throws MyException {
//        FileResDTO resDTO = new FileResDTO();
//        //判断是否有文件-用户关联关系
//        FileUser fileUser = new FileUser();
//        fileUser.setFileId(fileId);
//        fileUser.setUserId(UserUtils.getUserLocal().getId());
//        List<FileUser> fileUserList = fileUserMapper.queryListByParams(fileUser);
//        //创建文件用户关联
//        if(CollectionUtils.isEmpty(fileUserList)){
//            fileUser.setOrgName(fileName);
//            fileUser.setStatus(FileUserEnums.STATUS_0.getCode());
//            int count = fileUserMapper.insert(fileUser);
//            if(count != GlobalEnum.COUNT_1.getCode()){
//                throw new MyException(FileResultEnums.FILE_SAVE_FAIL.getCode(), FileResultEnums.FILE_SAVE_FAIL.getMsg());
//            }
//        }
//        resDTO.setId(fileId);
//        return GlobalResultDTO.SUCCESS(resDTO);
//    }
//
//    /**
//     * 新增服务器文件
//     * @param suffix
//     * @param md5Str
//     * @param fileBytes
//     * @return
//     */
//    private GlobalResultDTO insertServerFile(String suffix, String md5Str, byte[] fileBytes) throws  MyException {
//        File fileInsert = new File();
//        //自动生成的文件名
//        String fakeName = CodeGenerateUtils.codeGenerateByPrefix("f");
//        //文件夹路径
//        String folderPath = fileUploadRoot;
//        String filePathRelative = java.io.File.separator;
//        fileInsert.setName(fakeName);
//        fileInsert.setSuffix(suffix);
//        fileInsert.setMd5(md5Str);
//        fileInsert.setPathRoot(folderPath);
//        fileInsert.setPathRelative(filePathRelative);
//        fileInsert.setFileSize((long)fileBytes.length);
//        fileInsert.setStatus(FileEnums.STATUS_0.getCode());
//        //创建文件
//        java.io.File folder = new java.io.File(folderPath);
//        try {
//            if(!folder.exists()){
//                boolean mkdirs = folder.mkdirs();
//            }
//            String path = folderPath + filePathRelative + fakeName + "." + suffix;
//            FileUtils.writeByteArrayToFile(new java.io.File(path), fileBytes);
//        } catch (IOException e) {
//            throw new MyException(FileResultEnums.FILE_SAVE_FAIL.getCode(), FileResultEnums.FILE_SAVE_FAIL.getMsg());
//        }
//        if(fileMapper.insert(fileInsert) != GlobalEnum.COUNT_1.getCode()){
//            //FIXME 回滚，文件删除
//
//            throw new MyException(FileResultEnums.FILE_SAVE_FAIL.getCode(), FileResultEnums.FILE_SAVE_FAIL.getMsg());
//        }
//        return GlobalResultDTO.SUCCESS(fileInsert);
//    }
//
//    /**
//     * md5加密文件
//     * @param fileStr
//     * @return
//     */
//    private Map<String, Object> Md5EncryptFile(String fileStr) {
//        //获取文件名称
//        String suffix = fileStr.substring(fileStr.indexOf("/") + 1, fileStr.indexOf(";"));
//        fileStr = fileStr.replace(fileStr.substring(0, fileStr.indexOf(",") + 1), "");
//        byte[] fileBytes = Base64.getDecoder().decode(fileStr);
//        //文件MD5编码
//        String md5Str = null;
//        try {
//            md5Str = Md5Utils.md5Encoding(fileBytes);
//        } catch (IOException | NoSuchAlgorithmException e) {
//            throw new MyException(FileResultEnums.MD5_ENCODER_FAIL.getCode(), FileResultEnums.MD5_ENCODER_FAIL.getMsg());
//        }
//        Map<String, Object> fileMap = new HashMap<>();
//        fileMap.put(SUFFIX, suffix);
//        fileMap.put(FILE_BYTES, fileBytes);
//        fileMap.put(MD5_STR, md5Str);
//        return fileMap;
//    }
//}
