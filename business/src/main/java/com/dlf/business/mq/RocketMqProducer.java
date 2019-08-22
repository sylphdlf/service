package com.dlf.business.mq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;
@Component
public class RocketMqProducer {

    private static DefaultMQProducer producer;

    public static String defaultMQProducer(String messageInfo) {
        try {
            //生产者的组名
            DefaultMQProducer producer = new DefaultMQProducer("producer-group");
            //指定NameServer地址，多个地址以 ; 隔开
            producer.setNamesrvAddr("122.112.236.194:9876");
            producer.setCreateTopicKey("HAHA");
            /**
             * Producer对象在使用之前必须要调用start初始化，初始化一次即可
             * 注意：切记不可以在每次发送消息时，都调用start方法
             */
            producer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
        String resultMsg = "";
        try {
            //创建一个消息实例，包含 topic、tag 和 消息体
            //如下：topic 为 "TopicTest"，tag 为 "push"
//            Message message = new Message("service-producer", "push", messageInfo.getBytes());
//            StopWatch stop = new StopWatch();
//            stop.start();
//            SendResult result = producer.send(message, new MessageQueueSelector() {
//                @Override
//                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
//                    Integer id = (Integer) arg;
//                    int index = id % mqs.size();
//                    return mqs.get(index);
//                }
//            }, 1);
//            System.out.println("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
//            stop.stop();
//            resultMsg = result.getSendStatus().toString();
//            System.out.println("----------------发送1条消息耗时：" + stop.getTotalTimeMillis());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
        return resultMsg;
    }
}
