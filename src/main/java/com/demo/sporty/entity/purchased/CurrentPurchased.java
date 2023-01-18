package com.demo.sporty.entity.purchased;

import java.util.List;

public class CurrentPurchased {

	double total;
	List<PurchasedList> list;
	
	public CurrentPurchased() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurrentPurchased(double total, List<PurchasedList> list) {
		super();
		this.total = total;
		this.list = list;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<PurchasedList> getList() {
		return list;
	}

	public void setList(List<PurchasedList> list) {
		this.list = list;
	}
	
	
}