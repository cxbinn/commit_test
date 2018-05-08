package com.skywalker.basisModel;

import java.io.Serializable;
import java.util.Date;

public class API_User  implements Serializable{
	private String api_sn;
	private String user_sn;
	private String api_address;
	private String user_name;
	private Date join_date;
	public String getApi_sn() {
		return api_sn;
	}
	public void setApi_sn(String api_sn) {
		this.api_sn = api_sn;
	}
	public String getUser_sn() {
		return user_sn;
	}
	public void setUser_sn(String user_sn) {
		this.user_sn = user_sn;
	}
	public String getApi_address() {
		return api_address;
	}
	public void setApi_address(String api_address) {
		this.api_address = api_address;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	
}
