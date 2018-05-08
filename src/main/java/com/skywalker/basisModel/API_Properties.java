package com.skywalker.basisModel;

import java.io.Serializable;
import java.util.Date;

public class API_Properties implements Serializable {
	private String api_sn; 
	private String api_address;
	private String api_project_sn;
	private String project_name;
	private Date register_date;
	public String getApi_sn() {
		return api_sn;
	}
	public void setApi_sn(String api_sn) {
		this.api_sn = api_sn;
	}
	public String getApi_address() {
		return api_address;
	}
	public void setApi_address(String api_address) {
		this.api_address = api_address;
	}
	public String getApi_project_sn() {
		return api_project_sn;
	}
	public void setApi_project_sn(String api_project_sn) {
		this.api_project_sn = api_project_sn;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	
	

}
