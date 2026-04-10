package com.ex_mongo_2.demo_2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ex_mongo_2.demo_2.POJO.NewUser;
import com.ex_mongo_2.demo_2.repository.New_userRepo;


@SpringBootTest
public class new_userServicesTest {
	
	@Autowired
	private New_userRepo urepo;
	
	@Test
	public void TestfindByusername1() {
		assertEquals(4, 2+2);
		assertNotNull(urepo.findByUsername1("nihan"));
		assertTrue(5>3);
		NewUser user = urepo.findByUsername1("nihan");
		assertTrue(!user.getDataentry().isEmpty());
	}

}
