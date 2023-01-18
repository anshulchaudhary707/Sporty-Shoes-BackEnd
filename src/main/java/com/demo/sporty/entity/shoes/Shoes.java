package com.demo.sporty.entity.shoes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Shoes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column(unique=true)
	String shoesName;
	double price;
	String categoryName;
	
	@ManyToOne
	Category category;

	public Shoes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Shoes(String shoesName, double price, String categoryName) {
		super();
		this.shoesName = shoesName;
		this.price = price;
		this.categoryName = categoryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShoesName() {
		return shoesName;
	}

	public void setShoesName(String shoesName) {
		this.shoesName = shoesName;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}