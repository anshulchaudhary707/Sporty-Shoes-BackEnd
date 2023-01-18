package com.demo.sporty.entity.purchased;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class PurchasedList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String itemName;
	private double itemPrice;
	@ManyToOne
	private Purchased purchased;
	
	private Date date;
	private String email;
	private String category;
	
	public PurchasedList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PurchasedList(String itemName, double itemPrice) {
		super();
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Purchased getPurchased() {
		return purchased;
	}

	public void setPurchased(Purchased purchased) {
		this.purchased = purchased;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}