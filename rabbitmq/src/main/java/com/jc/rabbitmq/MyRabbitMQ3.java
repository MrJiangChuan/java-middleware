package com.jc.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "tag3")
public class MyRabbitMQ3 {

    @RabbitHandler
    public void getMessage(String msg){
        System.out.println(msg+3333);
    }
}
