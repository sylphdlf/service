package com.dlf.business.aspect;

import com.dlf.common.utils.DateTimeUtils;
import com.dlf.common.utils.user.ThreadUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class DBAspect {

    private static final Long DEFAULT_USER_ID = 0L;

    @Pointcut(value = "execution(* com.dlf.model.dao.*.*.save*(..))")
    private void beforeInsert(){};
//    @Pointcut(value = "execution(* com.dlf.model.mapper.*.*.delete*(..)) || execution(* com.dlf.model.mapper.*.*.update*(..))")
//    private void beforeDeleteOrUpdate(){};
//    //查询加入用户id
//    @Pointcut(value = "execution(* com.dlf.model.mapper.*.*.*ByUser(..)) || execution(* com.dlf.model.mapper.*.*.*ByPrimaryKey(..))")
//    private void beforeSelect(){};

    /**
     * 对于插入语句自动添加create_time, is_deleted
     * @param jp
     */
    @Before("beforeInsert()")
    public void insert(JoinPoint jp){
        if (jp.getArgs() == null) {
            return;
        }
        for(Object bean : jp.getArgs()){
            if(bean instanceof String){
                continue;
            }
            try {
//                if (PropertyUtils.getProperty(bean, "id") == null) {
//                    PropertyUtils.setProperty(bean, "id", CodeGenerateUtils.idGenerateByUUID());
//                }
                PropertyUtils.setProperty(bean, "createTime", new Date());
                if(PropertyUtils.getProperty(bean, "isDeleted") == null){
                    PropertyUtils.setProperty(bean, "isDeleted", 0);
                }
                try {
                    if(null == ThreadUser.getUserLocal() || null == ThreadUser.getUserId()){
                        PropertyUtils.setProperty(bean, "createUserId", DEFAULT_USER_ID);
                    }else{
                        PropertyUtils.setProperty(bean, "createUserId", ThreadUser.getUserId());
                    }
                }catch (Exception e){
                    PropertyUtils.setProperty(bean, "createUserId", 0);
                }
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
    }
    /**
     * 对于删除语句自动添加update_time, update_user_id
     * @param jp
     */
//    @Before("beforeDeleteOrUpdate()")
    public void delete(JoinPoint jp){
        if (jp.getArgs() == null) {
            return;
        }
        for(Object bean : jp.getArgs()){
            if(bean instanceof String){
                continue;
            }
            try {
                PropertyUtils.setProperty(bean, "updateTime", DateTimeUtils.getCurrentTimeStr());
                try {
                    PropertyUtils.setProperty(bean, "updateUserId", ThreadUser.getUserId());
                }catch (Exception e){
                    log.error(e.getMessage());
                }
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
    }

    /**
     * 对于查询语句自动添加createUserId
     * @param jp
     */
//    @Before("beforeSelect()")
    public void select(JoinPoint jp){
        if (jp.getArgs() == null) {
            return;
        }
        for(Object bean : jp.getArgs()){
            if(bean instanceof String){
                continue;
            }
            try {
                try {
                    PropertyUtils.setProperty(bean, "createUserId", ThreadUser.getUserId());
                }catch (Exception e){
                    log.error(e.getMessage());
                }
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
    }
}