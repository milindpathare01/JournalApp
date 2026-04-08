package com.ex_mongo_2.demo_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
public class Demo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}
	
	@Bean
	public MongoTransactionManager MongoTransactionManager(MongoDatabaseFactory db) {
		return new MongoTransactionManager(db);
	}

	
	@Bean
	public RestTemplate resteRestTemplate(){
		return new RestTemplate();
	}
}
