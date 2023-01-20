package com.demo.sporty.payload;

public class SearchUser {

	private String email;
	
	public SearchUser() {
		super();
	}
	
	public SearchUser(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
