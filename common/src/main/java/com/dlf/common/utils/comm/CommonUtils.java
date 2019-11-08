package com.dlf.common.utils.comm;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;

public class CommonUtils {

    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 验证入参
     */
    public static String validateObjectFields(Object obj, String[] fieldNames){
        StringBuilder returnStr = new StringBuilder();
        for(String thisFieldName : fieldNames){
            try {
                if(null == PropertyUtils.getProperty(obj, thisFieldName) ||
                        StringUtils.isEmpty(PropertyUtils.getProperty(obj, thisFieldName)))
                    returnStr.append(thisFieldName).append(",");
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.error(e.getMessage());
            }
        }
        if(!org.apache.commons.lang3.StringUtils.isBlank(returnStr)){
            String substring = returnStr.substring(0, returnStr.length() - 1);
            substring += " 必填";
            return substring;
        }
        return "";
    }
}
