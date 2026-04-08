package com.ex_mongo_2.demo_2.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailSendServicesTest {
	
	
	@Autowired
	private EmailSendServices mail;
	
	@Test
	void testmail() {
		mail.sendEmail("milindpathare932001@gmail.com", "JAVA Code", "hii bhava hot aalay!!!");
	}

}
