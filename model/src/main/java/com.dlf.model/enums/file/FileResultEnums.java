package com.dlf.model.enums.file;

import com.dlf.model.enums.ICommResultEnums;

public enum FileResultEnums implements ICommResultEnums {

    FILE_ALREADY_EXIST("file_001", "文件已经存在"),
    FILE_ALREADY_EXIST_BY_USER("file_002", "文件已经上传过了"),
    MD5_ENCODER_FAIL("file_003", "md5加密失败"),
    FILE_SAVE_FAIL("file_004", "文件保存失败"),
    FILE_GET_FAIL("file_005", "文件获取失败"),
    FILE_NOT_EXIST("file_006", "文件不存在"),
    FILE_INSERT_FAIL("file_007", "文件存入数据库失败"),
    FILE_IN_USE("file_008", "文件正在被引用"),
    FILE_DEL_FAIL("file_009", "文件删除失败"),
    ;
    private String code;
    private String msg;

    FileResultEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
