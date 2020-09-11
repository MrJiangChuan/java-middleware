package com.jc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisApplicationTests {

	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	@Test
	void set() {
		redisTemplate.opsForValue().set("user","kitty");
	}

	@Test
	void get() {
		String user = redisTemplate.opsForValue().get("user");
		System.out.println(user);
	}


}
