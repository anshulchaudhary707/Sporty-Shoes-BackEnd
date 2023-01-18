package com.demo.sporty.entity.add2cart;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Add2CartList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String itemName;
	@ManyToOne
	Add2Cart add2Cart;
	
	public Add2CartList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Add2CartList(String itemName) {
		super();
		this.itemName = itemName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}