package com.ex_mongo_2.demo_2.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ex_mongo_2.demo_2.POJO.NewUser;
import com.ex_mongo_2.demo_2.POJO.users;

@Repository
public interface New_userRepo extends MongoRepository<NewUser, String>{
	
	NewUser findByUsername1(String username1);

}
