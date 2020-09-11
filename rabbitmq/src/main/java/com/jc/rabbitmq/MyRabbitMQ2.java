package com.jc.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "tag2")
public class MyRabbitMQ2 {

    @RabbitHandler
    public void getMessage(String msg){
        System.out.println(msg+2222);
    }
}
