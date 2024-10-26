package com.hackathon.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private String name;
	private String email;
	private String phoneNumber;
	private String address;
	private String accountNumber;
	private String hashedPassword;
}
