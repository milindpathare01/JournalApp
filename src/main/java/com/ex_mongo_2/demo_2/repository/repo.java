package com.ex_mongo_2.demo_2.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ex_mongo_2.demo_2.POJO.JournalEntry;

public interface repo extends MongoRepository<JournalEntry, ObjectId>{

}
