package com.skywalker.basisModel;

import java.io.Serializable;
import java.util.Date;

public class Project_Properties implements Serializable {
	private String project_sn;
	private String project_name;
	private Date register_date;
	private String department;
	public String getProject_sn() {
		return project_sn;
	}
	public void setProject_sn(String project_sn) {
		this.project_sn = project_sn;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}
