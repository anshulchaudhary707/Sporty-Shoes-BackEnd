package com.demo.sporty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.sporty.payload.ShowUsers;

public interface ShowUsersRepository extends JpaRepository<ShowUsers,Integer> {

}
