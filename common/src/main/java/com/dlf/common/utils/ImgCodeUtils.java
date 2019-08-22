package com.dlf.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @DESCRIPTION 图片验证码工具类
 */
public class ImgCodeUtils {

    private static Logger logger = LoggerFactory.getLogger(ImgCodeUtils.class);
    private String randString = "123456789ABCDEFGHIJKLMNPQRSTUVWXYZ";//随机产生数字与字母组合的字符串
    private Random random = new Random();

    /**
     * 生成随机数
     */
    public String getRandomCode(){
        String randomCode = "";
        for (int i = 1; i <= 4; i++) {
            String rand = String.valueOf(randString.charAt(random.nextInt(randString.length())));
            randomCode += rand;
        }
        logger.info("randomCode:" + randomCode);
        return randomCode;
    }

}
