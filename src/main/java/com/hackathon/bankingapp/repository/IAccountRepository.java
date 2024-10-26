package com.hackathon.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.bankingapp.entity.Account;

public interface IAccountRepository extends JpaRepository<Account, Integer>{
	
}
