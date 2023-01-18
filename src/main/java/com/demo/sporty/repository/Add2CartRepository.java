package com.demo.sporty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.sporty.entity.add2cart.Add2Cart;


public interface Add2CartRepository extends JpaRepository<Add2Cart,Integer> {

	public Add2Cart findAdd2CartByUserEmail(String userEmail);
}