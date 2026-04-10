package com.ex_mongo_2.demo_2.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.bson.types.ObjectId;
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

import com.ex_mongo_2.demo_2.POJO.WheatherResponse;
import com.ex_mongo_2.demo_2.POJO.new_users_from_DB;
import com.ex_mongo_2.demo_2.POJO.users;
import com.ex_mongo_2.demo_2.repository.userRepo;
import com.ex_mongo_2.demo_2.service.new_userServices;
import com.ex_mongo_2.demo_2.service.userServices;
import com.ex_mongo_2.demo_2.service.wheatherService;

@RestController
@RequestMapping("/new_user")
public class userController {

	@Autowired
	private new_userServices services;
	
	@Autowired
	private wheatherService wheatherservice;
	
	@GetMapping()
	public List<new_users_from_DB> getAll() {
		return services.getall(); 
	}
	
	@GetMapping("id/{id}")
	public Optional<new_users_from_DB> getbyID(@PathVariable String id) {	
		
		Optional<new_users_from_DB> users = services.findByid(id);
		if(users.isPresent()) {
			return services.findByid(id);
		}
		return null;
		
	}
	
	@PutMapping()
	public ResponseEntity<?> updateDetails(@RequestBody new_users_from_DB newentry) {

		org.springframework.security.core.Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		new_users_from_DB userInDatabase = services.findByusername(username);
			
			userInDatabase.setUsername1(newentry.getUsername1());
			userInDatabase.setPassword(newentry.getPassword());
			services.saveNewEntry(userInDatabase);
			
		 return new ResponseEntity<new_users_from_DB>(HttpStatus.NO_CONTENT);
		
	}
	
	@DeleteMapping("id/{id}")
	public ResponseEntity<new_users_from_DB> updateDetails1(@PathVariable String id) {
		
		Optional<new_users_from_DB> userInDatabase = services.findByid(id);
		if(userInDatabase !=null) {
		services.deleteEntry(id);
		return new ResponseEntity<new_users_from_DB>(HttpStatus.OK);
		}
		return new ResponseEntity<new_users_from_DB>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/myuser")
	public ResponseEntity<?> getuser(){
		org.springframework.security.core.Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		 WheatherResponse reponse = wheatherservice.getWheather("Mumbai");
		 String greeting ="";
		 if(reponse !=null) {
			  greeting = " temp is " + reponse.getCurrent().getTemp_f();
		 }
		return new ResponseEntity<>("hii "+username +greeting,HttpStatus.OK);
	}
	//I am just cleaning my code
	
}

