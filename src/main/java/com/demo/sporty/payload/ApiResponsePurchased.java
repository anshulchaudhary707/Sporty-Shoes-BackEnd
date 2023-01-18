package com.demo.sporty.payload;

import com.demo.sporty.entity.purchased.CurrentPurchased;

public class ApiResponsePurchased {

	private String message;
	private boolean Success;
	CurrentPurchased cp;
	public ApiResponsePurchased() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ApiResponsePurchased(String message, boolean success, CurrentPurchased cp) {
		super();
		this.message = message;
		Success = success;
		this.cp = cp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return Success;
	}
	public void setSuccess(boolean success) {
		Success = success;
	}
	public CurrentPurchased getPl() {
		return cp;
	}
	public void setPl(CurrentPurchased cp) {
		this.cp = cp;
	}
}
