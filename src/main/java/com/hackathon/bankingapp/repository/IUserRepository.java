package com.hackathon.bankingapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.bankingapp.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByNameOrEmail(String name, String email);
	Optional<User> findByEmail(String email);
	Optional<User> findByName(String name);
	Boolean existsByPhoneNumber(String phone);
	Boolean existsByEmail(String email);

}
