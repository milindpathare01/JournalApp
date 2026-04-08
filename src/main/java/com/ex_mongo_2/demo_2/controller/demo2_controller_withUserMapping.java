package com.ex_mongo_2.demo_2.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex_mongo_2.demo_2.POJO.JournalEntry;
import com.ex_mongo_2.demo_2.POJO.new_users_from_DB;
import com.ex_mongo_2.demo_2.POJO.users;
import com.ex_mongo_2.demo_2.service.JournalEntry_service;
import com.ex_mongo_2.demo_2.service.new_userServices;
import com.ex_mongo_2.demo_2.service.userServices;

@RestController
@RequestMapping("/journal_res/connect")
public class demo2_controller_withUserMapping {
	
	@Autowired
	private JournalEntry_service service;
	
	@Autowired
	private userServices usersevices;
	
	@GetMapping("{username}")
	public ResponseEntity getAllJournalEnrtyofUser(@PathVariable String username){
		
		users u1 = usersevices.findByusername(username);
		
		List<JournalEntry> all = u1.getDataentry();
		if(all !=null && !all.isEmpty()) {
			return new ResponseEntity(all,HttpStatus.OK);
		}
		
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	} 
	
	
	@GetMapping("getAlluser/{username}")
	public ResponseEntity<JournalEntry> getbyID(@PathVariable String username) {
		
		users user= usersevices.findByusername(username);
		List<JournalEntry> all = user.getDataentry();
		if(all !=null && !all.equals("")) {
			return new ResponseEntity(all,HttpStatus.OK) ;
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);

	}
	
	@PutMapping("{username}/id/{id}")
	
	//For the mapping to which ID @Pathvariable is important 
	//AND for the input taking from the user through the postman or any then the @RequestBody is important.
	public ResponseEntity<?> update_record(@PathVariable ObjectId id,
			@PathVariable String username,
			@RequestBody JournalEntry newentry
			) {
		
		JournalEntry old = service.findbyID(id).orElse(null);
		
		if(old !=null) {
			if(newentry.getTitle() !=null && !newentry.getTitle().equals("")) {
				
				old.setTitle(newentry.getTitle());
			}
			else {
				old.getTitle();
			}
			if(newentry.getContent() !=null && !newentry.getContent().equals("")) {
				
				old.setContent(newentry.getContent());
			}
			else {
				old.getContent();
			}
			service.saveentry(old);
			return new ResponseEntity<>(old,HttpStatus.OK);
			
		}
		//newentry.setDate(LocalDateTime.now());
		
		//old.setTitle(newentry.getTitle()!=null && !newentry.getTitle().equals("")? newentry.getTitle() : old.getTitle());
		//old.setContent(newentry.getContent()!=null && !newentry.getContent().equals("")? newentry.getContent() : old.getContent());
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("{username}/id/{new_myid}")
	public ResponseEntity<?> deletebyID(
			@PathVariable ObjectId new_myid,
			@PathVariable String username) 
	{
		
		service.deleteEntry(new_myid,username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);		
	}

}
