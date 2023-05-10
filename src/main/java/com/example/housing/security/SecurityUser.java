package com.example.housing.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.housing.entity.Owner;

public class SecurityUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final Owner owner;

	public SecurityUser(Owner owner) {
		this.owner = owner;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return owner.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRoleName()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return owner.getPassword();
	}

	@Override
	public String getUsername() {
		return owner.getEmail();
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
		return true;
	}
}
