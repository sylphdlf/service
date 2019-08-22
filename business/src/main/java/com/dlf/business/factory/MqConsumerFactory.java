package com.dlf.business.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息消费者路由(初始化的时候加载)
 */
//@Component
public class MqConsumerFactory {
    //消息消费者路由
    public static final Map<String, Map<String, String>> CONSUMER_ROUTER_MAP = new HashMap<>();

    /**
     * 初始化
     */
    public static void setRouterMap(String topic, String tag, String serviceName, String methodName) {
        Map<String, String> tagMap;
        if(null == CONSUMER_ROUTER_MAP.get(topic)){
            tagMap = new HashMap<>();
            tagMap.put(tag, serviceName + "," + methodName);
            CONSUMER_ROUTER_MAP.put(topic, tagMap);
        }else{
            tagMap = CONSUMER_ROUTER_MAP.get(topic);
            tagMap.put(tag, serviceName + "," + methodName);
        }
    }
}
