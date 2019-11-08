//package com.dlf.business.aspect;
//
//import com.dlf.common.utils.CodeGenerateUtils;
//import com.dlf.common.utils.DateTimeUtils;
//import com.dlf.common.utils.user.UserUtils;
//import org.apache.commons.beanutils.PropertyUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class DBAspect {
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Pointcut(value = "execution(* org.springframework.data.jpa.repository.save*(..))")
//    private void beforeInsert(){};
////    @Pointcut(value = "execution(* com.dlf.model.mapper.*.*.delete*(..)) || execution(* com.dlf.model.mapper.*.*.update*(..))")
////    private void beforeDeleteOrUpdate(){};
////    //查询加入用户id
////    @Pointcut(value = "execution(* com.dlf.model.mapper.*.*.*ByUser(..)) || execution(* com.dlf.model.mapper.*.*.*ByPrimaryKey(..))")
////    private void beforeSelect(){};
//
//    /**
//     * 对于插入语句自动添加create_time, is_deleted
//     * @param jp
//     */
//    @Before("beforeInsert()")
//    public void insert(JoinPoint jp){
//        if (jp.getArgs() == null) {
//            return;
//        }
//        for(Object bean : jp.getArgs()){
//            if(bean instanceof String){
//                continue;
//            }
//            try {
//                if (PropertyUtils.getProperty(bean, "id") == null) {
//                    PropertyUtils.setProperty(bean, "id", CodeGenerateUtils.idGenerateByUUID());
//                }
//                PropertyUtils.setProperty(bean, "createTime", DateTimeUtils.getCurrentTimeStr());
//                if(PropertyUtils.getProperty(bean, "isDeleted") == null){
//                    PropertyUtils.setProperty(bean, "isDeleted", "0");
//                }
//                try {
//                    if (PropertyUtils.getProperty(bean, "createUserId") == null) {
//                        PropertyUtils.setProperty(bean, "createUserId", UserUtils.getUserLocal().getId());
//                    }
//                }catch (Exception e){
//                    PropertyUtils.setProperty(bean, "createUserId", "0");
//                }
//            }catch (Exception e){
//                logger.error(e.getMessage());
//            }
//        }
//    }
//    /**
//     * 对于删除语句自动添加update_time, update_user_id
//     * @param jp
//     */
////    @Before("beforeDeleteOrUpdate()")
//    public void delete(JoinPoint jp){
//        if (jp.getArgs() == null) {
//            return;
//        }
//        for(Object bean : jp.getArgs()){
//            if(bean instanceof String){
//                continue;
//            }
//            try {
//                PropertyUtils.setProperty(bean, "updateTime", DateTimeUtils.getCurrentTimeStr());
//                try {
//                    PropertyUtils.setProperty(bean, "updateUserId", UserUtils.getUserLocal().getId());
//                }catch (Exception e){
//                    logger.error(e.getMessage());
//                }
//            }catch (Exception e){
//                logger.error(e.getMessage());
//            }
//        }
//    }
//
//    /**
//     * 对于查询语句自动添加createUserId
//     * @param jp
//     */
////    @Before("beforeSelect()")
//    public void select(JoinPoint jp){
//        if (jp.getArgs() == null) {
//            return;
//        }
//        for(Object bean : jp.getArgs()){
//            if(bean instanceof String){
//                continue;
//            }
//            try {
//                try {
//                    PropertyUtils.setProperty(bean, "createUserId", UserUtils.getUserLocal().getId());
//                }catch (Exception e){
//                    logger.error(e.getMessage());
//                }
//            }catch (Exception e){
//                logger.error(e.getMessage());
//            }
//        }
//    }
//}