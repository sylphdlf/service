package com.dlf.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {
    /**
     * 转换为MD5字符串
     * @param bytes
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String transferFromBytes(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(bytes);
        return new BigInteger(1, digest).toString(16);
    }

    //MD5加密
    public static String md5Encoding(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        return Md5Utils.transferFromBytes(file.getBytes()).toUpperCase();
    }

    public static String md5Encoding(byte[] bytes) throws IOException, NoSuchAlgorithmException {
        return Md5Utils.transferFromBytes(bytes).toUpperCase();
    }
}
