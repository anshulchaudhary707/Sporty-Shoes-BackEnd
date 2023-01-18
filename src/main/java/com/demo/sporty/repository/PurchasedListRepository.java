package com.demo.sporty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.sporty.entity.purchased.PurchasedList;

public interface PurchasedListRepository extends JpaRepository<PurchasedList,Integer> {

	
}
