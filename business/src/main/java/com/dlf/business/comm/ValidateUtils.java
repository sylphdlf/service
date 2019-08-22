package com.dlf.business.comm;

import com.dlf.business.exception.MyException;
import com.dlf.common.utils.comm.CommonUtils;
import com.dlf.model.enums.GlobalResultEnum;
import org.springframework.util.StringUtils;

public class ValidateUtils {
    /**
     * 验证方法
     * @param params
     * @param fields
     * @throws MyException
     */
    public static void validate(Object params, String[] fields) throws MyException{
        //验证参数
        String returnStr = CommonUtils.validateObjectFields(params, fields);
        if(!StringUtils.isEmpty(returnStr))
            throw new MyException(GlobalResultEnum.MISSING_PARAMETER.getCode(), returnStr);
    }

    /**
     * 验证并且移除其他字段
     * @param params
     * @param fields
     * @throws MyException
     */
//    public static void validateAndRemove(Object params, String[] fields) throws MyException{
//        //验证参数
//        String returnStr = CommonUtils.validateObjectFields(params, fields);
//        if(!StringUtils.isEmpty(returnStr))
//            throw new MyException(GlobalResultEnum.MISSING_PARAMETER.getCode(), returnStr);
//    }
}
