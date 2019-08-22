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
     * @param obj
     * @param fieldNames
     * @return
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

//    private static int byteArrayToInt(byte[] b, int offset) {
//        int value = 0;
//        for (int i = 0; i < 4; i++) {
//            int shift = (4 - 1 - i) * 8;
//            value += (b[i + offset] & 0x000000FF) << shift;
//        }
//        return value;
//    }

//    private static int bytesToInt(byte[] bNum) {
//        int retInt = 0;
//        retInt = ((bNum[0] & 0xFF) << 24);
//        retInt += (bNum[1] & 0xFF) << 16;
//        retInt += (bNum[2] & 0xFF) << 8;
//        retInt += bNum[3] & 0xFF;
//        return retInt;
//    }
}
