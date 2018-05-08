package com.skywalker.basisModel;

import java.io.Serializable;
import java.util.Date;

public class API_Passport implements Serializable {
	private String api_sn;
	private String api_address;
	private String passport;
	private Date join_date;
	private int rest;
	private int all_count;
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
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	public int getRest() {
		return rest;
	}
	public void setRest(int rest) {
		this.rest = rest;
	}
	public int getAll_count() {
		return all_count;
	}
	public void setAll_count(int all_count) {
		this.all_count = all_count;
	}
	

}
