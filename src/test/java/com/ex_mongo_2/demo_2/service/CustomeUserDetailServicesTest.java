package com.ex_mongo_2.demo_2.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.ex_mongo_2.demo_2.POJO.NewUser;
import com.ex_mongo_2.demo_2.repository.New_userRepo;
import com.mongodb.assertions.Assertions;

@SpringBootTest
public class CustomeUserDetailServicesTest {
	
	

	@InjectMocks
	private CustomeUserDetailServices userdetails;
	
	
	//@mockbean is use for the test without any DATAASE entry and also replace the complete repo into fake repo.
	@MockBean
	private New_userRepo repo;
	
	
	@Disabled
	@Test
	void loadUserByUsernameTest() {
		
		when(repo.findByUsername1(ArgumentMatchers.anyString())).thenReturn((NewUser) User.builder().username("ram").password("ram").build());
		UserDetails user = userdetails.loadUserByUsername("ram");
		Assertions.assertNotNull(user);
	}

}
