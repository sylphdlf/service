package com.dlf.business.mq;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
@Component
public class RocketMqConsumer {

    public static void updateOwnerOrderCount() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer-group");
        consumer.setNamesrvAddr("122.112.236.194:9876");
        try {
            consumer.subscribe("service-producer", "");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            consumer.registerMessageListener(new MessageListenerOrderly() {
                AtomicLong consumeTimes = new AtomicLong(0);
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                    consumeOrderlyContext.setAutoCommit(true);
                    for (MessageExt msg : list) {
                        String msgContent = new String(msg.getBody());
                        System.out.println("######### MSG Content start ##########");
                        System.out.println(msgContent);
                        System.out.println("#########        END        ##########");
                        parseMsgContent(msgContent);
                        try {
                            TimeUnit.SECONDS.sleep(5L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });
            consumer.start();
            System.out.println("Cosumer started.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //解析消息内容
    private static void parseMsgContent(String msgContent) {
        JSONObject msg = JSONObject.parseObject(msgContent);
        System.out.println(msg);
//        String className = msg.getString("className");
//        if (className.equals(SysData.REAL_TIME_MSG_TYPE)) {
//            //解析实时回路信息
//            msgHandler.handleMsg(msgContent);
//
//        } else if (className.equals(SysData.TERMINAL_ONLINE_MSG_TYPE)) {
//            //解析终端在离线状态
//            msgHandler.handleOnlineTerminallist(msgContent);
//        }else if (className.equals(SysData.TERMINAL_SET_MSG_TYPE)){
//            //解析设置终端应答
//            msgHandler.handleSetTerminalReceived(msgContent);
//        }
    }
}
