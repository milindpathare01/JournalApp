package com.ex_mongo_2.demo_2.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex_mongo_2.demo_2.POJO.*;
import com.ex_mongo_2.demo_2.service.JournalEntry_service;

@RestController
@RequestMapping("/journal")
public class demo2_controller {
	
	@Autowired
	private JournalEntry_service service;
	
	@GetMapping
	public List<JournalEntry> getAll(){
		return service.getAll();
	}
	
	
	@PostMapping
	public JournalEntry createentry(@RequestBody JournalEntry p1) {
		p1.setDate(LocalDateTime.now());
		//service.saveentry(p1);
		return p1;
	}
	
	@GetMapping("/id/{myid}")
	public Optional<JournalEntry> getbyID(@PathVariable ObjectId myid) {
		return service.findbyID(myid);
	}
	
	@PutMapping("/id/{id}")
	
	//For the mapping to which ID @Pathvariable is important 
	//AND for the input taking from the user through the postman or any then the @RequestBody is important.
	public JournalEntry update_record(@PathVariable ObjectId id, @RequestBody JournalEntry newentry) {
		
		JournalEntry old = service.findbyID(id).orElse(null);
		
		
		if(old !=null) {
//			System.out.println("getTitle : " +newentry.getTitle());
//			System.out.println("getContent : " +newentry.getContent());
//			System.out.println("old getTitle : " +old.getTitle());
//			System.out.println("old getcontent : " +old.getContent());
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
		}
		//newentry.setDate(LocalDateTime.now());
		
		//old.setTitle(newentry.getTitle()!=null && !newentry.getTitle().equals("")? newentry.getTitle() : old.getTitle());
		//old.setContent(newentry.getContent()!=null && !newentry.getContent().equals("")? newentry.getContent() : old.getContent());
		//service.saveentry(old);
		return old;
	}
	
	@DeleteMapping("/id/{new_myid}")
	public boolean deletebyID(@PathVariable ObjectId new_myid) {
		
		//service.deleteEntry(new_myid);
		return true;
		
	}

}
