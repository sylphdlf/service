package com.dlf.api.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public aspect UserAspect {

    @Pointcut(value = "execution(* com.dlf.api.*.*Controller.*(..))")
    private void beforeEntrance(){};

    @Before("beforeEntrance()")
    public void beforeController(JoinPoint jp){
        if (jp.getArgs() == null) {
            return;
        }
        for(Object bean : jp.getArgs()){
            if(bean instanceof JSONObject){
                System.out.println(111);
                continue;
            }
        }
    }
}
