package com.hackathon.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpDto {
	
	private String name;
	private String password;
	private String email;
	private String address;
	private String phoneNumber;
}
