package com.dlf.common.utils.message;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 发送邮件工具类
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MailSendUtils {

    public static void send(JavaMailSender javaMailSender, String target, String from, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(target); //自己给自己发送邮件
        message.setSubject(subject);
        message.setText(content);
//        javaMailSender.send(message);
    }

    /**
     * 验证是否是邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            return matcher.matches();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
