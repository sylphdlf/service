//package com.dlf.business.manager.message.impl;
//
//import com.dlf.business.manager.message.RocketMqPushService;
//import com.dlf.model.dto.GlobalResultDTO;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.client.producer.SendCallback;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.remoting.common.RemotingHelper;
//import org.apache.rocketmq.remoting.exception.RemotingException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.UnsupportedEncodingException;
//
//@Service
//public class RocketMqPushServiceImpl implements RocketMqPushService {
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    DefaultMQProducer producer;
//
//    @Override
//    public GlobalResultDTO asyncPush(String str, String topic, String tags){
//
//        try {
//            logger.info("推送的消息：" + str);
//            Message msg = null;
//            msg = new Message(topic, tags, str.getBytes(RemotingHelper.DEFAULT_CHARSET));
//            producer.send(msg, new SendCallback() {
//                @Override
//                public void onSuccess(SendResult sendResult) {
//                    logger.info(sendResult.getMsgId());
//                }
//                @Override
//                public void onException(Throwable e) {
//                    logger.error(e.getMessage());
//                }
//            });
//        } catch (UnsupportedEncodingException | RemotingException | InterruptedException | MQClientException e) {
//            logger.error(e.getMessage());
//        }
//        return GlobalResultDTO.SUCCESS();
//    }
//}
