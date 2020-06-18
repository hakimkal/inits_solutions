package com.inits.expense.model;

import java.util.Set;

public class Response {
	public static final int SUCCESS = 0;
	public static final int FAILED = 1;
	
	private int status;
	private String message;
	
	private Set<Expense> data;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Set<Expense> getData() {
		return data;
	}
	public void setData(Set<Expense> data) {
		this.data = data;
	}
	
}
