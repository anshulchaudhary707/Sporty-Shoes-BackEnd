package com.demo.sporty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.sporty.entity.add2cart.Add2CartList;
import com.demo.sporty.entity.purchased.CurrentPurchased;
import com.demo.sporty.entity.purchased.Purchased;
import com.demo.sporty.payload.ApiResponse;
import com.demo.sporty.payload.ApiResponsePurchased;
import com.demo.sporty.service.UserService;
import com.demo.sporty.repository.ShoesRepository;
import com.demo.sporty.entity.shoes.Shoes;
import com.demo.sporty.exception.ApiException;

@RestController
@RequestMapping("/api/v1/normal")
public class UserController {
	
	@Autowired
	private UserService us;

	@Autowired
	private ShoesRepository sr;
	
	public UserController(UserService us) {
		super();
		this.us = us;
	}

	@PostMapping("/add2Cart")
	public ResponseEntity<ApiResponse> add2Cart(@RequestBody Add2CartList p) {
		
		if(p.getItemName() == null) {
			throw new ApiException("Don't Enter blank value. Also use itemName variable to insert value.");
		}
		try {
			Shoes s = sr.findByShoesName(p.getItemName());
			System.out.println(s.getShoesName());
		}catch(Exception e){
			throw new ApiException("Please enter correct itemName.");
		}
		
		System.out.println("From Controller");
		System.out.println(p.getItemName());
		us.saveToCart(p);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Item successfully added to cart.",true),HttpStatus.OK);
	}
	
	@GetMapping("/purchase")
	public ResponseEntity<ApiResponsePurchased> purchaseFromCart() {
		CurrentPurchased cp = us.purchaseFromCart();
		return new ResponseEntity<ApiResponsePurchased>(new ApiResponsePurchased("Item purchased.",true,cp),HttpStatus.OK);
	}
	
	@GetMapping("/viewCart")
	public ResponseEntity<List<Add2CartList>> viewCart() {
		List<Add2CartList> l1 = us.viewCart();
		return new ResponseEntity<List<Add2CartList>>(l1,HttpStatus.OK);
	}
	@GetMapping("/emptyCart")
	public ResponseEntity<String> emptyCart() {
		us.emptyCart();
		return new ResponseEntity<String>("Your Cart is Empty Now.",HttpStatus.OK);
	}
	@GetMapping("/history")
	public ResponseEntity<Purchased> viewHistory() {
		return new ResponseEntity<Purchased>(us.viewHistory(),HttpStatus.OK);
	}
}