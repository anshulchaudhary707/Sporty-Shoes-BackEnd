package com.demo.sporty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.sporty.entity.User;
import com.demo.sporty.entity.role.Role;
import com.demo.sporty.payload.AuthenticationRequest;
import com.demo.sporty.payload.AuthenticationResponse;
import com.demo.sporty.payload.RegisterRequest;
import com.demo.sporty.repository.RoleRepository;
import com.demo.sporty.repository.UserRepository;
import com.demo.sporty.security.JwtService;

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
	private AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) {
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		Role role = this.roleRepo.findById(502);
		user.getRoles().add(role);

		repository.save(user);

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
}
