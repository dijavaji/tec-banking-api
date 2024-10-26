package com.hackathon.bankingapp.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails{
	
	private final User user;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities  = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getUserType().getName()));
		//return Collections.emptyList();
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.user.getStatus();
	}
	
	public String getName() {
		return this.user.getName();
	}
	
	public String getEmail() {
		return this.user.getEmail();
	}
	
	public Integer getId() {
		return this.user.getId();
	}
	
	public UserType getUserType() {
		return this.user.getUserType();
	}

	@Override
	public String getUsername() {
		return this.user.getName();
	}

}