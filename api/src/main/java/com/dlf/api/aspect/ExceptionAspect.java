package com.dlf.api.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {

    @Pointcut(value = "execution(* com.dlf.api.MyExceptionAdvice.*(..))")
    private void beforeException(){};

    @Before("beforeException()")
    public void mqPush(JoinPoint jp){

    }
}
