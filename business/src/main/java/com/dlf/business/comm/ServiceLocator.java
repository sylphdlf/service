//package com.dlf.business.comm;
//
//import com.dlf.model.enums.ICommEnums;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Component
//public class ServiceLocator implements ApplicationContextAware {
//
//    private static Map<String, ICommEnums> map;
//
//    /**
//     * 获取应用上下文并获取相应的接口实现类
//     * @param applicationContext
//     * @throws BeansException
//     */
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        //根据接口类型返回相应的所有bean
//        map = applicationContext.getBeansOfType(ICommEnums.class);
//    }
//
//    public static Map<String, ICommEnums> getMap() {
//        return map;
//    }
//}
