package com.dlf.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 * Date: 2017/8/22
 *
 * @author dailf@zendaimoney.com
 */
public class DateTimeUtils {

    protected static Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);

    public static final String HOURS_AND_MINUTES = "hh:mm";

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String CODE_PATTERN = "yyyyMMddHHmmss";
    /**
     * 时间转换
     * @param timeStr
     * @return
     */
    public static Date transferStrToDate(String timeStr, String formatter){
        try {
            DateFormat df = new SimpleDateFormat(formatter);
            return df.parse(timeStr);
        }catch (ParseException e) {
            logger.error("时间转换出错");
        }
        return null;
    }

    /**
     * 获取格式化后的日期
     * @param formatter
     * @return
     */
    public static String getCurrentTimeStr(String formatter){
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.format(new Date());
    }

    public static String getCurrentTimeStr(){
        return getCurrentTimeStr(DEFAULT_PATTERN);
    }

    public static String getCurrentTimeStrForCode(){
        return getCurrentTimeStr(CODE_PATTERN);
    }

    public static String timeFormat(Date date, String formatter){
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.format(date);
    }
}
