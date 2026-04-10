package com.ex_mongo_2.demo_2.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex_mongo_2.demo_2.POJO.JournalEntry;
import com.ex_mongo_2.demo_2.POJO.NewUser;
import com.ex_mongo_2.demo_2.POJO.users;
import com.ex_mongo_2.demo_2.service.CustomeUserDetailServices;
import com.ex_mongo_2.demo_2.service.NewUserService;
import com.ex_mongo_2.demo_2.service.userServices;
import com.ex_mongo_2.demo_2.utils.Jwtutility;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/public")
@Slf4j
public class public_controller {
	
	@Autowired
	private NewUserService services;
	
	@Autowired
	private CustomeUserDetailServices detail_service;
	
	@Autowired
	private AuthenticationManager auth;
	
	@Autowired
	private Jwtutility jwt;
	
	@PostMapping("/signup")
	public void creatuser(@RequestBody NewUser u1) {
		services.saveNewEntry(u1);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody NewUser u1) {
		
		try {
			auth.authenticate(new UsernamePasswordAuthenticationToken(u1.getUsername1(),u1.getPassword()));
			UserDetails details =  detail_service.loadUserByUsername(u1.getUsername1());
			String token =jwt.generateToken(details.getUsername());
			return new ResponseEntity(token,HttpStatus.OK);
				
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Incorrect username and password",HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	
	


}
