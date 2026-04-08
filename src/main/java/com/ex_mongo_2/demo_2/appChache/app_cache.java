package com.ex_mongo_2.demo_2.appChache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ex_mongo_2.demo_2.POJO.config_details;
import com.ex_mongo_2.demo_2.repository.config_details_Repo;

@Component
public class app_cache {
	
	@Autowired
	private config_details_Repo repo;
	
	 public Map<String, String> appcache;
	 
	 
	 @PostConstruct
	 public void init() {
		appcache = new HashMap<String, String>();
		List<config_details> details = repo.findAll();
		 for(config_details c1 : details) {
			 appcache.put(c1.getKey(), c1.getValue());
		 }
	 }
}
