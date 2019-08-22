package com.dlf.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @DESCRIPTION 校验规则工具类
 */
public class RuleUtils {

    /**
     * @DESCRIPTION: 校验登录密码规则
     */
    public static boolean checkPassword(String password) {
        String regEx = "^(?![A-Za-z]+$)(?![A-Z\\d]+$)(?![A-Z\\W]+$)(?![a-z\\d]+$)(?![a-z\\W]+$)(?![\\d\\W]+$)\\S{8,20}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    /**
     * @DESCRIPTION: 校验身份证规则
    */
    public static boolean checkIdCardNo(String idCardNo) {
        String regEx = "^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|31)|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}([0-9]|x|X)$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(idCardNo);
        return matcher.matches();
    }

    /**
     * @DESCRIPTION: 校验是否为两位中文
     */
    public static boolean checkTwoChinese(String word) {
        String regEx = "^[\u4E00-\u9FA5]{2}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(word);
        return matcher.matches();
    }
}
