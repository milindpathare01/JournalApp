package com.ex_mongo_2.demo_2.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ex_mongo_2.demo_2.repository.usermongoImpl;


@SpringBootTest
public class usermongoImplTest {
	
	
	@Autowired
	private usermongoImpl mongorepo;
	
	@Test
	public void test_user_mongo() {
		mongorepo.getUserSA();
	}
	
	

}
