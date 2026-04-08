package com.ex_mongo_2.demo_2.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ex_mongo_2.demo_2.POJO.config_details;
import com.ex_mongo_2.demo_2.POJO.new_users_from_DB;
import com.ex_mongo_2.demo_2.POJO.users;

@Repository
public interface config_details_Repo extends MongoRepository<config_details, String>{
	
	
}
