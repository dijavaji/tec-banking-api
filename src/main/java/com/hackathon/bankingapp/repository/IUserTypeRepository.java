package com.hackathon.bankingapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.bankingapp.entity.UserType;

public interface IUserTypeRepository extends JpaRepository<UserType, Integer>{
	
	Optional<UserType> findByName(String name);
}
