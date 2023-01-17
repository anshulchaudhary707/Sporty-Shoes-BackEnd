package com.demo.sporty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.sporty.entity.role.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

	public Role findById(int id);
}
