package com.demo.sporty.entity.add2cart;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;



@Entity
public class Add2Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(unique = true)
	String userEmail;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Add2CartList> purchases;

	public Add2Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Add2Cart(String userEmail) {
		super();
		this.userEmail = userEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return userEmail;
	}

	public void setName(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<Add2CartList> getList() {
		return purchases;
	}

	public void setList(List<Add2CartList> purchases) {
		this.purchases = purchases;
	}
	
}