package com.demo.sporty.entity.purchased;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Purchased {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<PurchasedList> items;
	double totalMoneySpent;
	String userEmail;
	public Purchased() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Purchased(List<PurchasedList> items, double totalMoneySpent) {
		super();
		this.items = items;
		this.totalMoneySpent = totalMoneySpent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<PurchasedList> getItems() {
		return items;
	}
	public void setItems(List<PurchasedList> items) {
		this.items = items;
	}
	public double getTotalPrice() {
		return totalMoneySpent;
	}
	public void setTotalPrice(double totalMoneySpent) {
		this.totalMoneySpent = totalMoneySpent;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}