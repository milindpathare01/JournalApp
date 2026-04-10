package com.ex_mongo_2.demo_2.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.ex_mongo_2.demo_2.POJO.NewUser;

@Component
public class usermongoImpl {
	
	
	@Autowired
	MongoTemplate temp;
	
	public List<NewUser> getUserSA(){
		
		Query q1 = new Query();
		q1.addCriteria(Criteria.where("username1").is("vimal"));
		q1.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"));
		q1.addCriteria(Criteria.where("sentiments").is(true));
		List<NewUser> user =  temp.find(q1, NewUser.class);
		return user;
	}

}
