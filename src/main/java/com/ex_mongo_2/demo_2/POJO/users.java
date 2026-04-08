package com.ex_mongo_2.demo_2.POJO;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

@Document(collection = "users")
public class users {
	
	@Id
	private ObjectId id;
	
	@Indexed(unique = true)
	@NonNull
	private String username;
	
	@NonNull
	private String password;
	
	@DBRef   //this will take the refrence of the pojo to link with the USER.
	private List<JournalEntry> dataentry = new ArrayList<>();
	
	

	public List<JournalEntry> getDataentry() {
		return dataentry;
	}

	public void setDataentry(List<JournalEntry> dataentry) {
		this.dataentry = dataentry;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
