package com.skywalker.basisModel;

import java.io.Serializable;
import java.util.Date;

public class Voucher_Order implements Serializable {
	private String order_sn;
	private Date order_init_date;
	private int kind;
	private String passport;
	private float money;
	private int status;
	private Date deal_date;
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public Date getorder_init_date() {
		return order_init_date;
	}
	public void setorder_init_date(Date order_init_date) {
		this.order_init_date = order_init_date;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getDeal_date() {
		return deal_date;
	}
	public void setDeal_date(Date deal_date) {
		this.deal_date = deal_date;
	}


}
