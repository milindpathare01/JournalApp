package com.ex_mongo_2.demo_2.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ex_mongo_2.demo_2.POJO.NewUser;
import com.ex_mongo_2.demo_2.POJO.users;
import com.ex_mongo_2.demo_2.repository.New_userRepo;
import com.ex_mongo_2.demo_2.repository.userRepo;


@Service
@Component
//All business logic will be entered here.
public class NewUserService {
	
	private static final PasswordEncoder pass = new BCryptPasswordEncoder();
	
	@Autowired
	private New_userRepo urepo;
	
	public void saveNewEntry(NewUser u1) {
		u1.setUsername1(u1.getUsername1());
		u1.setPassword(pass.encode(u1.getPassword()));
		u1.setRoles(Arrays.asList("USER"));
		urepo.save(u1);
	}
	
	public void saveuser(NewUser u1) {
		urepo.save(u1);
	}
	
	public List<NewUser> getall(){
		return urepo.findAll();
	}
	
	public Optional<NewUser> findByid(String id) {
		return Optional.ofNullable(urepo.findById(id).orElse(null));
	}
	
	public void deleteEntry(String id) {
		urepo.deleteById(id);
	}
	
	public NewUser findByusername(String username) {
		return urepo.findByUsername1(username);
		//temp commnd
	}

	public void saveAdmin(NewUser u1) {
		// TODO Auto-generated method stub
		u1.setUsername1(u1.getUsername1());
		u1.setPassword(pass.encode(u1.getPassword()));
		u1.setRoles(Arrays.asList("USER","ADMIN"));
		urepo.save(u1);
		
	}

	
}
