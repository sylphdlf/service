package com.dlf.api.config.mq;

import com.dlf.model.enums.mq.MqTopicInterface;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RocketMqConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${rocketMq.nameSrv.addr}")
    private String nameSrvAddr;
    @Value("${rocketMq.nameSrv.port}")
    private String nameSrvPort;

    @Autowired
    private MQConsumeListener mqConsumeListener;

    @Bean
    public DefaultMQProducer getDefaultProducer(){
        DefaultMQProducer producer = new DefaultMQProducer("matServiceProducer");
        // Specify name server addresses.
        producer.setNamesrvAddr(nameSrvAddr + ":" + nameSrvPort);
        //Launch the instance.
        try {
            producer.start();
            logger.info("producer is start !!!");
        } catch (MQClientException e) {
            logger.error(e.getMessage());
        }
        producer.setRetryTimesWhenSendAsyncFailed(0);
        return producer;
    }

    /**
     * 创建消息消费的实例
     */
    @Bean
    public DefaultMQPushConsumer getRocketMQConsumer(){

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("matServiceConsumer");
        consumer.setNamesrvAddr(nameSrvAddr + ":" + nameSrvPort);
        consumer.setConsumeThreadMin(20);
        consumer.setConsumeThreadMax(64);
        consumer.registerMessageListener(mqConsumeListener);

        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        /**
         * 设置消费模型，集群还是广播，默认为集群
         */
        //consumer.setMessageModel(MessageModel.CLUSTERING);

        /**
         * 设置一次消费消息的条数，默认为1条
         */
        consumer.setConsumeMessageBatchMaxSize(1);

        try {
            /**
             * 设置该消费者订阅的主题和tag，如果是订阅该主题下的所有tag，
             * 则tag使用*；如果需要指定订阅该主题下的某些tag，则使用||分割，例如tag1||tag2||tag3
             */
        /*String[] topicTagsArr = topics.split(";");
        for (String topicTags : topicTagsArr) {
            String[] topicTag = topicTags.split("~");
            consumer.subscribe(topicTag[0],topicTag[1]);
        }*/
//            consumer.subscribe(MqTopicInterface.CARGO_TOPIC, "*");
//            consumer.subscribe(MqTopicInterface.ORDER_TOPIC, "*");
//            consumer.subscribe(MqTopicInterface.MSG_TOPIC, "*");
//            consumer.subscribe(MqTopicInterface.SCORE_COUNT_TOPIC, "*");

            consumer.start();
            //logger.info("consumer is start !!! groupName:{},topics:{},namesrvAddr:{}",groupName,topics,namesrvAddr);

        } catch (Exception e) {
             logger.error(e.getMessage());
        }
        return consumer;
    }
}

