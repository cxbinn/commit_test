package com.skywalker.basisModel;

import java.io.Serializable;
import java.util.Date;

public class Manager_Properties implements Serializable {
	private String manager_sn;
	private String name;
	private String password;
	private String email;
	private String safeword;
	private int level;
	private Date register_date;
	public String getManager_sn() {
		return manager_sn;
	}
	public void setManager_sn(String manager_sn) {
		this.manager_sn = manager_sn;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	
	
}
