package com.demo.sporty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.sporty.entity.shoes.Shoes;

public interface ShoesRepository extends JpaRepository<Shoes,Integer> {

	public Shoes findByShoesName(String shoesName);
	
	List<Shoes> findAll();
	//ByOrderByCategoryAsc
}