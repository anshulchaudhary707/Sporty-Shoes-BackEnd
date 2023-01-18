package com.demo.sporty.payload;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShowShoes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column(unique=true)
	String shoesName;
	String shoesCategory;
	double price;
	
	public ShowShoes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShowShoes(String shoesName, String shoesCategory, double price) {
		super();
		this.shoesName = shoesName;
		this.shoesCategory = shoesCategory;
		this.price = price;
	}

	public String getShoesName() {
		return shoesName;
	}

	public void setShoesName(String shoesName) {
		this.shoesName = shoesName;
	}

	public String getShoesCategory() {
		return shoesCategory;
	}

	public void setShoesCategory(String shoesCategory) {
		this.shoesCategory = shoesCategory;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
