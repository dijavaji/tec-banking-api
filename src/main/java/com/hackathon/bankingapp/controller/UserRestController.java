package com.hackathon.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.bankingapp.common.exception.BankingException;
import com.hackathon.bankingapp.dto.JwtAuthResponseDto;
import com.hackathon.bankingapp.dto.LoginDto;
import com.hackathon.bankingapp.dto.SignUpDto;
import com.hackathon.bankingapp.dto.UserDto;
import com.hackathon.bankingapp.service.IAuthService;
import com.hackathon.bankingapp.service.IUserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author dvasquez
 */
//@CrossOrigin(origins = {"http://127.0.0.1:4200"})
@CrossOrigin(origins = {"/**"})
@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserRestController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IAuthService authService;
	
	@Value("${spring.application.name}")
	private String appName;
	
	@GetMapping
    public String getMessage() {
        return String.format("Now this finally works out. Welcome %s",appName);
    }
	
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> addUser(@Valid @RequestBody SignUpDto userDto, BindingResult result) {
		try {
			UserDto createUser = this.userService.createUser(userDto);
			return new ResponseEntity<>(createUser, HttpStatus.CREATED); 
		}catch(BankingException e) {
			String response = e.getMessage() +" : " + e;
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			log.error("Error al momento de crear usuario. {}",e);
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
		try {
			String token = authService.login(loginDto);
	        JwtAuthResponseDto jwtAuthResponse = new JwtAuthResponseDto();
	        jwtAuthResponse.setAccessToken(token);
	        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
		}catch(BankingException e) {
			String response = e.getMessage() +" : " + e;
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}catch(Exception e) {
			log.error("Error al momento de logger usuario. {}",e);
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
}
