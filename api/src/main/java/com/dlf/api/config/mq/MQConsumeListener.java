package com.dlf.api.config.mq;

import com.dlf.business.factory.MqConsumerFactory;
import com.dlf.business.mq.MqRouter;
import com.dlf.common.utils.comm.SpringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Component
public class MQConsumeListener implements MessageListenerConcurrently {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if(CollectionUtils.isEmpty(msgs)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = msgs.get(0);
        logger.info("接收到的消息是："+ new String(messageExt.getBody()));
        logger.info(messageExt.getTopic());
        MqRouter.router(messageExt);
        this.route(messageExt);
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    /**
     * 路由
     * @param messageExt
     */
    private void route(MessageExt messageExt) {
        try {

            String serviceAndMethodName = MqConsumerFactory.CONSUMER_ROUTER_MAP.get(messageExt.getTopic()).get(messageExt.getTags());
            String serviceName = serviceAndMethodName.split(",")[0];
            String methodName = serviceAndMethodName.split(",")[1];
            Object bean = SpringUtils.getBean(serviceName);
            Method method = bean.getClass().getMethod(methodName, byte[].class);
            method.invoke(bean, (Object) messageExt.getBody());
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
