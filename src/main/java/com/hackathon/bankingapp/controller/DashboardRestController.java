package com.hackathon.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.bankingapp.config.auth.TokenProvider;
import com.hackathon.bankingapp.dto.AccountDto;
import com.hackathon.bankingapp.dto.UserDto;
import com.hackathon.bankingapp.service.IAccountService;
import com.hackathon.bankingapp.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {"/**"})
@RestController
@RequestMapping("/api/dashboard")
@Slf4j
public class DashboardRestController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private TokenProvider tokenprovider;
	@Autowired
	private IAccountService accountService;
	
	@GetMapping("/user")
	public ResponseEntity<?> getUserByEmail(@RequestHeader (name="Authorization") String authParam) {
		
		String token = this.tokenprovider.recoverToken(authParam);
		String mail = tokenprovider.getUsername(token);
		log.info("busco user {}", mail);
		
		UserDto userDto = this.userService.getUserByEmail(mail);
		return ResponseEntity.ok(userDto); 
	}
	
	@GetMapping("/account")
	public ResponseEntity<?> getAccountByEmail(@RequestHeader (name="Authorization") String authParam) {
		String token = this.tokenprovider.recoverToken(authParam);
		String mail = tokenprovider.getUsername(token);
		log.info("busco acc {}", mail);
		
		AccountDto account = accountService.findOneByMailUser(mail);
		return ResponseEntity.ok(account); 
	}
}
