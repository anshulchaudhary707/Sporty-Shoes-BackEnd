package com.demo.sporty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.sporty.entity.shoes.Category;
import com.demo.sporty.entity.shoes.Shoes;
import com.demo.sporty.payload.ApiResponse;
import com.demo.sporty.service.AuthenticationService;
import com.demo.sporty.service.ShoesService;
import com.demo.sporty.payload.ShowUsers;
import com.demo.sporty.payload.ShowPurchasedList;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	@Autowired
	ShoesService ss;

	@Autowired
	private AuthenticationService service;
	
	public AdminController(ShoesService ss) {
		super();
		this.ss = ss;
	}
	
	//insert new category
	@PostMapping("/category")
	public ResponseEntity<ApiResponse> saveCategory(@RequestBody Category c) {
		ss.saveCategory(c);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Saved Successfully",true),HttpStatus.OK);
	}
	
	//insert new shoes
	@PostMapping("/shoes")
	public ResponseEntity<ApiResponse> saveShoes(@RequestBody Shoes s) {
		ss.saveShoes(s);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Shoes Saved Successfully",true),HttpStatus.OK);
	}
	
	
	@GetMapping("/allUsers")
	public List<ShowUsers> allUsers() {
		return service.getAllUsers();
	}
	
	@GetMapping("/allPurchases")
	public List<ShowPurchasedList> allPurchases() {
		return service.getAllPurchased();
	}
}