package com.ex_mongo_2.demo_2.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex_mongo_2.demo_2.POJO.JournalEntry;
import com.ex_mongo_2.demo_2.POJO.new_users_from_DB;
import com.ex_mongo_2.demo_2.POJO.users;
import com.ex_mongo_2.demo_2.repository.New_userRepo;
import com.ex_mongo_2.demo_2.repository.repo;


@Service
@Component
public class JournalEntry_service {

	
	@Autowired
	private repo r1;
	
	@Autowired
	private new_userServices userservice;
	
	
	//This method used for the Complete new user save not For Update.
	@Transactional    
	//this transactional annotation will help to sync One function as a single one container , 
	//if any exception found or Error caught then all action will be rollbacked.
	public void saveentry(JournalEntry p1,String username) {
		new_users_from_DB u1 = userservice.findByusername(username);
		p1.setDate(LocalDateTime.now());
		JournalEntry data =  r1.save(p1);
		u1.getDataentry().add(data); //IMP line where the in the perticular user it will be add a data entry.
		userservice.saveuser(u1);  //to save the Entry into the USER data with the all Entry data.
	}
	
	public List<JournalEntry> getAll(){
		return r1.findAll();
	}
	
	public Optional<JournalEntry> findbyID(ObjectId id){
		 return Optional.ofNullable(r1.findById(id).orElse(null));
	}
	
	@Transactional
	public void deleteEntry(ObjectId id, String username) {
		try {
			new_users_from_DB user = userservice.findByusername(username);
			boolean removed = user.getDataentry().removeIf(x -> x.getId().equals(id));   //this will delete the entry sync id with entry also and from the USER Also.
			if(removed) {
				userservice.saveNewEntry(user);  //When the Same id will getting 
				//saved here then Automatically update the record with Deletion of the record.
				r1.deleteById(id);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			throw new RuntimeException("error",e);
		}
		
	}
	
	//this method used for the update only.
	public void saveentry(JournalEntry p1) {
		r1.save(p1);
	}
	
	public List<JournalEntry_service> findByUserName(String username){
		return null;
		
	}
	
}
