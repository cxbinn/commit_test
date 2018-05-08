package com.skywalker.basisModel;

import java.io.Serializable;

public class Sys_User implements Serializable {
	private int id;
	private String name;
	private String password;
	private String email;
	private String safeword;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getSafeword() {
		return safeword;
	}
	public void setSafeword(String safeword) {
		this.safeword = safeword;
	}
	
	

}
