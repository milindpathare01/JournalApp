package com.ex_mongo_2.demo_2.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ex_mongo_2.demo_2.POJO.new_users_from_DB;
import com.ex_mongo_2.demo_2.POJO.users;

@Repository
public interface New_userRepo extends MongoRepository<new_users_from_DB, String>{
	
	new_users_from_DB findByUsername1(String username1);

}
