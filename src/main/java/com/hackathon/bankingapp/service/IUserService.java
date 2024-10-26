package com.hackathon.bankingapp.service;

import com.hackathon.bankingapp.common.exception.BankingException;
import com.hackathon.bankingapp.dto.SignUpDto;
import com.hackathon.bankingapp.dto.UserDto;

public interface IUserService {
	
	UserDto createUser(SignUpDto signUpDto) throws BankingException;
	
	SignUpDto getUserByEmail(String email) throws BankingException;

}
