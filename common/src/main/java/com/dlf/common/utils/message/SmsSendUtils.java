package com.dlf.common.utils.message;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 发送短信工具类
 */
public class SmsSendUtils {

    public static void send(String target, String content){
        
    }
    /**
     * 验证手机号码，11位数字，1开通，第二位数必须是3456789这些数字之一 *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        // Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
        Pattern regex = Pattern.compile("^1[3456789]\\d{9}$");
        Matcher matcher = regex.matcher(mobile);
        return matcher.matches();
    }
}
