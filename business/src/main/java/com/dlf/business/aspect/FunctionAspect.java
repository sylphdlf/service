package com.dlf.business.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FunctionAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 方法的执行时间 注解
     */
    @Pointcut("@annotation(com.dlf.business.anno.ExecuteTimeAnno)")
    private void executeTime(){};

    /**
     * 对于插入语句自动添加create_time, is_deleted
     * @param joinPoint
     */
    @Around("executeTime()")
    public Object calculate(ProceedingJoinPoint joinPoint){
        Object result = null;
        try {
            long start = System.currentTimeMillis();

            //有返回参数 则需返回值
            result =  joinPoint.proceed();

            long end = System.currentTimeMillis();
            logger.info("方法名称：" + joinPoint.getSignature());
            logger.info("总共执行时长" + (end - start) + " 毫秒");
        } catch (Throwable t) {
            logger.error("FunctionAspect:" + t.getMessage());
        }
        return result;
    }
}