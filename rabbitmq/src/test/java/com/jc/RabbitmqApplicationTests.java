package com.jc;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqApplicationTests {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test
	void rabbitDirectSend() {
		Object o = rabbitTemplate.convertSendAndReceive("tag1", "直连模式");
//		System.out.println(o);
	}

	@Test
	void rabbitFanoutSend() {
		Object o = rabbitTemplate.convertSendAndReceive("cat", "","分裂模式");
//		System.out.println(o);
	}

	@Test
	void rabbitTopicSend1() {
		Object o = rabbitTemplate.convertSendAndReceive("dog", "book.tt","主题模式");
//		System.out.println(o);
	}

	@Test
	void rabbitTopicSend2() {
		Object o = rabbitTemplate.convertSendAndReceive("dog", "sleep.cc","主题模式");
//		System.out.println(o);
	}

	@Test
	void rabbitTopicSend3() {
		Object o = rabbitTemplate.convertSendAndReceive("dog", "coding","主题模式");
//		System.out.println(o);
	}

}
