package com.hackathon.bankingapp.mapper;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.hackathon.bankingapp.common.BankingConstants;
import com.hackathon.bankingapp.dto.SignUpDto;
import com.hackathon.bankingapp.dto.UserDto;
import com.hackathon.bankingapp.entity.User;

public class UserMapper {
	
	public static User mapToUser(SignUpDto userDto) {
		User user = new User();
		user.setAddress(userDto.getAddress());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setName(userDto.getName());
		user.setCreatedBy(BankingConstants.CREATED_BY);
		return user;
	}
	
	public static UserDto mapToUserDto(User user, UUID uuid) {
		return new UserDto(
				user.getName(),
				user.getEmail(),
				user.getPhoneNumber(),
				user.getAddress(),
				uuid.toString(),
				user.getPassword()
				);
	}

}
