package com.demo.sporty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.sporty.payload.ShowPurchasedList;

public interface ShowPurchasedListRepository extends JpaRepository<ShowPurchasedList, Integer> {

	List<ShowPurchasedList> findByOrderByDateDesc();
}
