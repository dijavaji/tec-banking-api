package com.hackathon.bankingapp.dto;

import lombok.Data;

@Data
public class AuthCredentialDto {
	
	private String email;	//se puede utilizar UsernameOrEmail
	private String password;
}
