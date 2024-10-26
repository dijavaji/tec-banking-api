package com.hackathon.bankingapp.service;

import com.hackathon.bankingapp.dto.LoginDto;

public interface IAuthService {
	
	String login(LoginDto loginDto);
}
