package com.ex_mongo_2.demo_2.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ex_mongo_2.demo_2.POJO.new_users_from_DB;
import com.ex_mongo_2.demo_2.POJO.users;
import com.ex_mongo_2.demo_2.repository.New_userRepo;
import com.ex_mongo_2.demo_2.repository.userRepo;


@Service
@Component
//All business logic will be entered here.
public class userServices {
	
	private static final PasswordEncoder pass = new BCryptPasswordEncoder();
	
	@Autowired
	private userRepo urepo;
	
	public void saveeuser(users u1) {
		u1.setPassword(pass.encode(u1.getPassword()));
		urepo.save(u1);
	}
	
	public List<users> getall(){
		return urepo.findAll();
	}
	
	public Optional<users> findByid(ObjectId id) {
		return Optional.ofNullable(urepo.findById(id).orElse(null));
	}
	
	public void deleteEntry(ObjectId id) {
		urepo.deleteById(id);
	}
	
	public users findByusername(String username) {
		return urepo.findByusername(username);
	}

	
}
