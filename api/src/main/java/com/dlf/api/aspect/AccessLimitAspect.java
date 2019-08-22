//package com.dlf.api.aspect;
//
//import MyException;
//import RedisService;
//import UserUtils;
//import com.dlf.com.dlf.model.enums.comm.AccessLimitConfig;
//import com.dlf.com.dlf.model.enums.user.UserResultEnums;
//import org.apache.commons.beanutils.PropertyUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//@Aspect
//@Component
//@Order(2)
//public class AccessLimitAspect {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    RedisService redisService;
//
//    @Pointcut(value = "execution(* com.dlf.api.*.*Controller.*(..))")
//    private void beforeRequest(){};
//    /**
//     * 限制访问次数
//     * @param jp
//     */
//    @Before("beforeRequest()")
//    public void accessLimit(JoinPoint jp) throws MyException{
//        if (jp.getArgs() == null) {
//            return;
//        }
//        for(Object bean : jp.getArgs()){
//            if(null == bean){
//                continue;
//            }
//            if(bean instanceof String){
//                continue;
//            }
//            if(bean instanceof MultipartFile){
//                continue;
//            }
//            String userId = "";
//            String action = "";
//            String accessCount;
//            try {
//                userId = UserUtils.getUserLocal().getId();
//                action = (String)PropertyUtils.getProperty(bean, "action");
//            }catch (Exception e){
//                logger.error(e.getMessage());
//            }
//            if (StringUtils.isNotBlank(userId)) {
//                String accessLimitKey = action + "_" + userId;
//                accessCount = redisService.get(accessLimitKey);
//                if (StringUtils.isBlank(accessCount)) {
//                    redisService.put(accessLimitKey, AccessLimitConfig.INIT_COUNT, AccessLimitConfig.LIMIT_TIME);
//                } else if (Integer.valueOf(accessCount) < AccessLimitConfig.LIMIT_COUNT) {
//                    redisService.incr(accessLimitKey);
//                } else {
//                    throw new MyException(UserResultEnums.OPERATING_FREQUENCY.getCode(), UserResultEnums.OPERATING_FREQUENCY.getMsg());
//                }
//            }
//        }
//    }
//}