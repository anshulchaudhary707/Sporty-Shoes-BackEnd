package com.demo.sporty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.sporty.payload.ShowShoes;

public interface ShowShoesRepository extends JpaRepository<ShowShoes, Integer> {
	List<ShowShoes> findByOrderByShoesCategoryAsc();
}