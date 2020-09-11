package com.jc.mongodb;

import com.jc.mongodb.entity.Account;
import com.jc.mongodb.entity.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class MongodbApplicationTests {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	void insertUser() {
		User insert = mongoTemplate.insert(new User(UUID.randomUUID().toString(), "孙尚香", 25, "女"));
		System.out.println(insert);
	}

	@Test
	void findUser() {
		List<User> all = mongoTemplate.findAll(User.class);
		for (User user : all) {
			System.out.println(user);
		}
	}

	@Test
	void findByIdUser() {
		User byId = mongoTemplate.findById("b56c16e6-43e7-4fa1-ad78-cfaa74882a6b", User.class);
		System.out.println(byId);
	}

	@Test
	void findbyIfUser() {
		Query query = new Query(Criteria.where("age").gt(25));
		List<User> users = mongoTemplate.find(query, User.class);
		System.out.println(users);
	}

	@Test
	void updateUser(){
		Query query = new Query(Criteria.where("name").is("姜川"));
		Update update1 = new Update();
		update1.set("age",11).set("sex","女");
		UpdateResult upsert = mongoTemplate.upsert(query, update1, User.class);
		System.out.println(upsert);
	}


	@Test
	void deleteUser(){
		Query query = new Query(Criteria.where("name").is("姜川"));
		DeleteResult remove = mongoTemplate.remove(query, User.class);
		System.out.println(remove);
	}

	@Test
	void insertAccount() {
		Account tom = mongoTemplate.insert(new Account(UUID.randomUUID().toString(), "Tom", 1200f));
		System.out.println(tom);
	}


}
