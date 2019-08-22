package com.dlf.business.aspect;

import com.dlf.business.anno.RedisCacheAnno;
import com.dlf.business.manager.redis.RedisService;
import com.dlf.model.enums.GlobalResultEnum;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RedisCacheAspect {

    private Logger logger = LoggerFactory.getLogger(RedisCacheAspect.class);
    @Autowired
    RedisService redisService;
    /**
     * 方法开头从缓存中获取数据，方法结尾设置缓存
     * @param joinPoint
     */
    @Around(value = "@annotation(anno))")
    public Object getAndSet(ProceedingJoinPoint joinPoint, RedisCacheAnno anno){
        Object result = null;
        try {
            String key = "";
            if(StringUtils.isBlank(anno.key())){
                //用方法名称作为缓存的key
                key = joinPoint.getSignature().getName();
            }else{
                key = anno.key();
            }
            result = redisService.getObj(key);
            if(null == result){
                //缓存中没有数据，则执行方法
                result =  joinPoint.proceed();
                //设置缓存和超时时间
                redisService.put(key, result, anno.timeout());
            }else{
                logger.info(GlobalResultEnum.CACHE.getMsg());
                return result;
            }
        } catch (Throwable t) {
            logger.error(t.getMessage());
        }
        return result;
    }
}