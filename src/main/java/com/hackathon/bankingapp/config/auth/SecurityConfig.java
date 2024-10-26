package com.hackathon.bankingapp.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hackathon.bankingapp.security.SecurityFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private SecurityFilter securityFilter;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,  AuthenticationManager authManager) throws Exception {
		
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		
		//httpSecurity.authorizeHttpRequests();
		/*return httpSecurity.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.POST, "/api/users/*").permitAll()
						.requestMatchers(HttpMethod.GET, "/api/users/*").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/v1/books").hasRole("ADMIN").anyRequest()
						.authenticated())
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();*/
		
		httpSecurity.csrf(csrf -> csrf.disable());
		httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		httpSecurity.authorizeHttpRequests((authorize) ->{
			authorize.requestMatchers("/v3/api-docs/**", "/swagger-ui/**","/swagger-ui.html").permitAll();
            authorize.requestMatchers("/api/users/**").permitAll();
            authorize.requestMatchers(HttpMethod.POST, "/api/users/**").permitAll();
            authorize.anyRequest().authenticated();
            }
        );
		httpSecurity.addFilter(jwtAuthenticationFilter).addFilterBefore(this.securityFilter, UsernamePasswordAuthenticationFilter.class);;
		return httpSecurity.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
