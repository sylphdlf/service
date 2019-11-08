package com.dlf.business.aspect;

import com.dlf.business.anno.ValidateAnno;
import com.dlf.business.exception.MyException;
import com.dlf.common.utils.comm.CommonUtils;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.enums.GlobalResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
@Slf4j
public class ValidateAspect {
    /**
     * 非空验证
     * @return Object
     */
    @Around(value = "@annotation(anno))")
    public Object validate(ProceedingJoinPoint jp, ValidateAnno anno) throws MyException{
        Object bean = jp.getArgs()[0];
        String[] keys = anno.names();
        String returnStr = CommonUtils.validateObjectFields(bean, keys);
        if(StringUtils.isNotBlank(returnStr)){
            throw new MyException(GlobalResultEnum.MISSING_PARAMETER.getCode(), returnStr);
        }
        try {
            return jp.proceed();
        } catch (MyException e) {
            throw new MyException(e.getErrorCode(), e.getMessage());
        } catch (Throwable throwable){
            log.error(throwable.getMessage());
        }
        return GlobalResultDTO.FAIL();
    }
}