package com.ex_mongo_2.demo_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ex_mongo_2.demo_2.POJO.NewUser;
import com.ex_mongo_2.demo_2.repository.New_userRepo;


@Component
public class CustomeUserDetailServices implements UserDetailsService{

	
	@Autowired
	private New_userRepo new_userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    NewUser user = new_userRepo.findByUsername1(username);

	   if(user!=null) {
		   
		   return org.springframework.security.core.userdetails.User.builder()
				   .username(user.getUsername1())
				   .password(user.getPassword())
				   .roles(user.getRoles().toArray(new String[0]))
				   .build();
				  
				   }
	 throw new UsernameNotFoundException("username not found : " + username);
	}
}
