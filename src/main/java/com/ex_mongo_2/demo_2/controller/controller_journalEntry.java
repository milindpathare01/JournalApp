package com.ex_mongo_2.demo_2.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex_mongo_2.demo_2.POJO.JournalEntry;
import com.ex_mongo_2.demo_2.POJO.NewUser;
import com.ex_mongo_2.demo_2.POJO.users;
import com.ex_mongo_2.demo_2.service.JournalEntryService;
import com.ex_mongo_2.demo_2.service.NewUserService;
import com.ex_mongo_2.demo_2.service.userServices;

import org.slf4j.Logger;



@RestController
@RequestMapping("/journal_connection")
public class controller_journalEntry {
	
	private static final Logger log =  LoggerFactory.getLogger(controller_journalEntry.class);
	
	@Autowired
	private JournalEntryService service;
	
	@Autowired
	private NewUserService usersevices;
	
	@GetMapping
	public ResponseEntity getAllJournalEnrtyofUser(){
		
		org.springframework.security.core.Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		NewUser user = usersevices.findByusername(username);
		List<JournalEntry> all = user.getDataentry();
		if(all !=null && !all.isEmpty()) {
			return new ResponseEntity(all,HttpStatus.OK);
		}
		
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	} 
	
	
	@PostMapping
	public ResponseEntity<JournalEntry> createentry(@RequestBody JournalEntry p1) {
		try {
			org.springframework.security.core.Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			p1.setDate(LocalDateTime.now());
			service.saveentry(p1,username);
			return new ResponseEntity(p1,HttpStatus.CREATED);
		}
		catch (Exception e) {
			// TODO: handle exception
			log.error("error found :"+e);
			return new ResponseEntity(p1,HttpStatus.BAD_REQUEST);
		}
		
	} 
	
	@GetMapping("id/{id}")
	
	public ResponseEntity<?> getByIdContent(@PathVariable ObjectId id){
		org.springframework.security.core.Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
	    NewUser user = 	usersevices.findByusername(username);
	    List<JournalEntry> list =  user.getDataentry().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
	    
	  if(!list.isEmpty()) {
		  Optional<JournalEntry> journal = service.findbyID(id);
		  if(journal.isPresent()) {
			  return new ResponseEntity<>(journal.get(),HttpStatus.FOUND);
		  }
	  }
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
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
	
	@DeleteMapping("/id/{new_myid}")
	public ResponseEntity<?> deletebyID(
			@PathVariable ObjectId new_myid) 
	{
		org.springframework.security.core.Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		service.deleteEntry(new_myid,username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);		
	}

}
