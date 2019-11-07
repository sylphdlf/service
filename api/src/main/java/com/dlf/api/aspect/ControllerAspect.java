//package com.dlf.api.aspect;
//
//import com.dlf.business.manager.redis.RedisService;
//import com.dlf.business.manager.user.UserService;
//import com.dlf.common.utils.user.ThreadUser;
//import com.dlf.common.utils.user.UserUtils;
//import org.apache.commons.beanutils.PropertyUtils;
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
//import javax.annotation.Resource;
//
//@Aspect
//@Component
//@Order(1)
//public class ControllerAspect {
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    UserService userService;
//    @Autowired
//    RedisService redisService;
//
//    @Pointcut(value = "execution(* com.dlf.api.*.*Controller.*(..))")
//    private void beforeRequest(){};
//    /**
//     * 对于插入语句自动添加create_time, is_deleted
//     * @param jp
//     */
//    @Before("beforeRequest()")
//    public void getUser(JoinPoint jp){
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
//            try {
//                String userId = (String) PropertyUtils.getProperty(bean, "userId");
//                logger.info("user access:" + userId);
//                ThreadUser threadUser = new ThreadUser();
//                threadUser.setId(userId);
//                UserUtils.setUserLocal(threadUser);
//            }catch (Exception e){
//                logger.error(e.getMessage());
//            }
//        }
//    }
//}