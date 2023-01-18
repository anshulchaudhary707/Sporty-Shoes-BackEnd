package com.demo.sporty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.sporty.entity.shoes.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByCategoryName(String categoryNme);
}
