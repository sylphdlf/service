//package com.dlf.api.listeners;
//
//import com.dlf.business.anno.MqRouterAnno;
//import com.dlf.business.factory.MqConsumerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.lang.reflect.Method;
//import java.util.Map;
//
///**
// * Created by Administrator on 2017/5/7.
// */
//@Component
//public class ContextInitListener implements ApplicationListener<ContextRefreshedEvent> {
//
//    private Logger logger = LoggerFactory.getLogger(ContextInitListener.class);
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(Service.class);
//        for (Map.Entry<String, Object> thisBeanEntry : beans.entrySet()){
//            Method[] methods = thisBeanEntry.getValue().getClass().getMethods();
//            for(Method thisMethod: methods){
//                MqRouterAnno annotation = AnnotationUtils.findAnnotation(thisMethod, MqRouterAnno.class);
//                if(null != annotation){
//                    MqConsumerFactory.setRouterMap(annotation.topic(), annotation.tags(), thisBeanEntry.getKey(), thisMethod.getName());
//                }
//            }
//        }
//        logger.info("初始化mq注解:" + MqConsumerFactory.CONSUMER_ROUTER_MAP.toString());
//    }
//}