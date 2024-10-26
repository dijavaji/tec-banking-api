package com.hackathon.bankingapp.config.auth;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.bankingapp.dto.AuthCredentialDto;
import com.hackathon.bankingapp.entity.UserDetailsImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@SuppressWarnings("static-access")
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		AuthCredentialDto authCredential = new AuthCredentialDto();
		
		try {
			
			authCredential = new ObjectMapper().readValue(request.getReader(), AuthCredentialDto.class);
		}catch(Exception e) {
			log.error("Error credenciales no enviadas.",e);
		}
		
		UsernamePasswordAuthenticationToken usernamePat = new UsernamePasswordAuthenticationToken(
				authCredential.getEmail(), authCredential.getPassword(), Collections.emptyList());
		
		return getAuthenticationManager().authenticate(usernamePat);
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
		String token = tokenProvider.createToken(userDetails.getUsername(), userDetails.getEmail());
		log.info("generado successfulAuthentication {}",token);
		response.addHeader("Authorization", "Bearer " + token);
		
		response.getWriter().flush();
	}
}
