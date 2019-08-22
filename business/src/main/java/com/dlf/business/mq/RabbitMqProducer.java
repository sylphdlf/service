/*
package com.dlf.business.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RabbitMqProducer {

    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Producer : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}
*/
