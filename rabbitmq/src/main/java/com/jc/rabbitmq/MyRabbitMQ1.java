package com.jc.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "tag1")
public class MyRabbitMQ1 {

    @RabbitHandler
    public void getMessage1(String msg){
        System.out.println(msg+1111);
    }

}
