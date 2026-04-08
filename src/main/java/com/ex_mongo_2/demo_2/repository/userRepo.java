package com.ex_mongo_2.demo_2.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ex_mongo_2.demo_2.POJO.users;

public interface userRepo extends MongoRepository<users, ObjectId>{
	
	users findByusername(String username);

}
