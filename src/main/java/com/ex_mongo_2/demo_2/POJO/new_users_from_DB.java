package com.ex_mongo_2.demo_2.POJO;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "new_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class new_users_from_DB {
	
	@Id
	private ObjectId id;
	
	@Indexed(unique = true)
	@NonNull
	private String username1;
	private String email;
	private boolean sentiments;
	
	@NonNull
	private String password;
	
	@DBRef   //this will take the refrence of the pojo to link with the USER.
	private List<JournalEntry> dataentry = new ArrayList<>();
	private List<String> roles;
	
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getUsername1() {
		return username1;
	}
	public void setUsername1(String username) {
		this.username1 = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<JournalEntry> getDataentry() {
		return dataentry;
	}
	public void setDataentry(List<JournalEntry> dataentry) {
		this.dataentry = dataentry;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	

}
