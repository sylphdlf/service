package com.dlf.business.mq;

import org.apache.rocketmq.common.message.MessageExt;

public class MqRouter {

    public static void router(MessageExt messageExt){
        messageExt.getTopic();
        messageExt.getTags();
    }
}
