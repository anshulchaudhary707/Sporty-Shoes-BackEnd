package com.demo.sporty.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.sporty.entity.User;
import com.demo.sporty.entity.add2cart.Add2Cart;
import com.demo.sporty.entity.add2cart.Add2CartList;
import com.demo.sporty.entity.purchased.Purchased;
import com.demo.sporty.entity.purchased.PurchasedList;
import com.demo.sporty.entity.role.Role;
import com.demo.sporty.exception.ApiException;
import com.demo.sporty.exception.IntegrityConstraintViolation;
import com.demo.sporty.payload.AuthenticationRequest;
import com.demo.sporty.payload.AuthenticationResponse;
import com.demo.sporty.payload.ChangePassword;
import com.demo.sporty.payload.RegisterRequest;
import com.demo.sporty.repository.RoleRepository;
import com.demo.sporty.repository.UserRepository;
import com.demo.sporty.security.JwtService;
import com.demo.sporty.payload.ShowUsers;
import com.demo.sporty.repository.ShowUsersRepository;
import com.demo.sporty.payload.ShowPurchasedList;
import com.demo.sporty.repository.ShowPurchasedListRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private ShowUsersRepository sur;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ShowPurchasedListRepository splr;

	public AuthenticationResponse registerAdmin(RegisterRequest request) {

		if (request.getName() == null || request.getName().length() == 0) {
			throw new ApiException("Enter correct name");
		}
		if (request.getEmail() == null || request.getEmail().length() == 0) {
			throw new ApiException("Enter correct email");
		}
		if (request.getPassword() == null || request.getPassword().length() == 0) {
			throw new ApiException("Enter correct password");
		}

		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		Role role = this.roleRepo.findById(501);
		user.getRoles().add(role);

		// creating add2cart for user
		Add2Cart ac = new Add2Cart();
		ac.setName(user.getEmail());

		// creating purchased for user
		Purchased p = new Purchased();
		p.setUserEmail(user.getEmail());

		// creating add2cart list for user
		List<Add2CartList> l1 = new ArrayList<>();
		ac.setList(l1);

		// creating purchased list for user
		List<PurchasedList> l2 = new ArrayList<>();
		p.setItems(l2);

		// adding add2cart for user
		user.setAdd2Cart(ac);

		// adding purchased for user
		user.setPurchased(p);
		
		ShowUsers su1 = new ShowUsers();
		su1.setEmail(user.getEmail());
		su1.setName(user.getName());
		su1.setPassword(user.getPassword());

		try {
			repository.save(user);
			sur.save(su1);
		} catch (DataIntegrityViolationException sx) {
			throw new IntegrityConstraintViolation(user.getEmail(), "user");
		}

		String jwtToken = jwtService.generateToken(user);

		return new AuthenticationResponse(jwtToken);
	}

	public AuthenticationResponse registerNormal(RegisterRequest request) {
		if (request.getName() == null || request.getName().length() == 0) {
			throw new ApiException("Enter correct name");
		}
		if (request.getEmail() == null || request.getEmail().length() == 0) {
			throw new ApiException("Enter correct email");
		}
		if (request.getPassword() == null || request.getPassword().length() == 0) {
			throw new ApiException("Enter correct password");
		}

		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		Role role = this.roleRepo.findById(502);
		user.getRoles().add(role);

		// creating add2cart for user
		Add2Cart ac = new Add2Cart();
		ac.setName(user.getEmail());

		// creating purchased for user
		Purchased p = new Purchased();
		p.setUserEmail(user.getEmail());

		// creating add2cart list for user
		List<Add2CartList> l1 = new ArrayList<>();
		ac.setList(l1);

		// creating purchased list for user
		List<PurchasedList> l2 = new ArrayList<>();
		p.setItems(l2);

		// adding add2cart for user
		user.setAdd2Cart(ac);

		// adding purchased for user
		user.setPurchased(p);
		
		ShowUsers su1 = new ShowUsers();
		su1.setEmail(user.getEmail());
		su1.setName(user.getName());
		su1.setPassword(user.getPassword());

		try {
			repository.save(user);
			sur.save(su1);
		} catch (DataIntegrityViolationException sx) {
			throw new IntegrityConstraintViolation(user.getEmail(), "user");
		}

		String jwtToken = jwtService.generateToken(user);

		return new AuthenticationResponse(jwtToken);
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		User user = repository.findByEmail(request.getEmail()).orElseThrow();

		String jwtToken = jwtService.generateToken(user);

		return new AuthenticationResponse(jwtToken);
	}
	
	public String changePassword(ChangePassword cp) {
		User u1 = repository.findByEmail(cp.getEmail()).orElseThrow();
		u1.setPassword(passwordEncoder.encode(cp.getPassword()));
		repository.save(u1);
		return "Password Changed Successfully";
	}
	
	public List<ShowUsers> getAllUsers(){
		return sur.findAll();
	}
	
	public List<ShowPurchasedList> getAllPurchased(){
		return splr.findByOrderByShowPurchasedListDateDesc();
	}
 }
