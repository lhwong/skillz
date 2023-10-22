package com.skillzstreet.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {
	
	@Id
    String id;
    String name;
    String username;
    String password;
    String email;
    @JsonProperty("honor_code")
    boolean honorCode = true;
    @JsonProperty("term_of_service")
    boolean termOfService = true;
    boolean verified = true;
    
    public User() {
    }
    
	public User(String id, String name) {
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isHonorCode() {
		return honorCode;
	}

	public void setHonorCode(boolean honorCode) {
		this.honorCode = honorCode;
	}

	public boolean isTermOfService() {
		return termOfService;
	}

	public void setTermOfService(boolean termOfService) {
		this.termOfService = termOfService;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
    
    
    

}
