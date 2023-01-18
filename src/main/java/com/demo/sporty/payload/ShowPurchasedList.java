package com.demo.sporty.payload;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShowPurchasedList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	private String itemName;
	private String category;
	private double price;
	private Date date;
	private String email;
	public ShowPurchasedList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShowPurchasedList(int id, String itemName, String category, double price, Date date, String email) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.category = category;
		this.price = price;
		this.date = date;
		this.email = email;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
