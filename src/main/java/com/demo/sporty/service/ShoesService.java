package com.demo.sporty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.demo.sporty.entity.shoes.Category;
import com.demo.sporty.entity.shoes.Shoes;
import com.demo.sporty.exception.IntegrityConstraintViolation;
import com.demo.sporty.repository.CategoryRepository;
import com.demo.sporty.repository.ShoesRepository;
import com.demo.sporty.payload.ShowShoes;
import com.demo.sporty.repository.ShowShoesRepository;

@Service
public class ShoesService {

	@Autowired
	private ShoesRepository sr;

	@Autowired
	private CategoryRepository cr;

	@Autowired
	private ShowShoesRepository ssr;
	
	// saving shoes
	public void saveShoes(Shoes s) {
		try {
			Category c = cr.findByCategoryName(s.getCategoryName());
			if(c == null) {
				c = new Category(s.getCategoryName());
				cr.save(c);
			}
			ShowShoes s1 = new ShowShoes();
			s1.setShoesName(s.getShoesName());
			s1.setShoesCategory(s.getCategoryName());
			s1.setPrice(s.getPrice());
			ssr.save(s1);
			s.setCategory(c);
			sr.save(s);
		}

		catch(DataIntegrityViolationException sx) {
			throw new IntegrityConstraintViolation(s.getShoesName(),"shoes");
		}
	}

	// saving category
	public void saveCategory(Category c) {
		try {
			this.cr.save(c);
		}
		catch(DataIntegrityViolationException sx) {
			throw new IntegrityConstraintViolation(c.getCategoryName(),"category");
		}
	}
	
	// get all shoes
	public List<ShowShoes> getAllShoes() {
		return ssr.findByOrderByShoesCategoryAsc();
	}
}