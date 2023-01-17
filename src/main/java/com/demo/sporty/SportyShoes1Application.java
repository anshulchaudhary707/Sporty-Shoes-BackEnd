package com.demo.sporty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.sporty.entity.role.Role;
import com.demo.sporty.repository.RoleRepository;

@SpringBootApplication
public class SportyShoes1Application implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SportyShoes1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		try {
			Role role1 = new Role(501,"ROLE_ADMIN");
			Role role2 = new Role(502,"ROLE_NORMAL");
			roleRepo.save(role1);
			roleRepo.save(role2);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
