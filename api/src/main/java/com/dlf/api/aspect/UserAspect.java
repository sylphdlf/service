package com.dlf.api.aspect;

import com.alibaba.fastjson.JSONObject;
import com.dlf.common.utils.user.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class UserAspect {

    public UserAspect() {
        System.out.println("UserAspect");
    }

    @Pointcut(value = "execution(* com.dlf.api.controller..*.*(..))")
    private void beforeEntrance(){}

    @Before("beforeEntrance()")
    public void beforeController(JoinPoint jp){
        if (jp.getArgs() == null) {
            System.out.println("jp.getArgs()");
            return;
        }
        for(Object bean : jp.getArgs()){
            if(bean != null){
                try {
                    Object userId = PropertyUtils.getProperty(bean, "userId");
                    if(null != userId){
                        UserUtils.setUserId((Long)userId);
                    }
                }catch (Exception e){
                    log.error(e.getMessage());
                }
            }
        }
    }
}
