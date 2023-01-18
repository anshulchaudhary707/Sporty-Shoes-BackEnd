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

import com.demo.sporty.payload.AuthenticationRequest;
import com.demo.sporty.payload.AuthenticationResponse;
import com.demo.sporty.payload.RegisterRequest;
import com.demo.sporty.service.AuthenticationService;
import com.demo.sporty.service.ShoesService;
import com.demo.sporty.payload.ShowShoes;
import com.demo.sporty.payload.ChangePassword;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationService service;
	
	@Autowired
	private ShoesService ss;
	
	@PostMapping("/register/admin")
	public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody RegisterRequest request){
			return ResponseEntity.ok(service.registerAdmin(request));
	}
	
	@PostMapping("/register/normal")
	public ResponseEntity<AuthenticationResponse> registerNormal(@RequestBody RegisterRequest request){
			return ResponseEntity.ok(service.registerNormal(request));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
		return ResponseEntity.ok(service.authenticate(request));
	}
	
	@PostMapping("/password")
	public ResponseEntity<String> changePassword(@RequestBody ChangePassword request){
		return ResponseEntity.ok(service.changePassword(request));
	}
	
	@GetMapping("/allShoes")
	public ResponseEntity<List<ShowShoes>> getAllShoes(){
		return new ResponseEntity<List<ShowShoes>>(ss.getAllShoes(),HttpStatus.OK);
	}
}
