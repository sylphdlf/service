/*
package com.dlf.business.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "service")
public class RabbitMqConsumer {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Consumer  : " + hello);
    }

}
*/
