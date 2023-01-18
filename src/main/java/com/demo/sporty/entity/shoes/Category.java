package com.demo.sporty.entity.shoes;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column(unique = true)
	String categoryName;

	@OneToMany(mappedBy = "category")
	List<Shoes> list1;

	public Category() {
		super();
	}

	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Shoes> getList1() {
		return list1;
	}

	public void setList1(List<Shoes> list1) {
		this.list1 = list1;
	}
}
