package com.cos.web01.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.web01.domain.user.Users;

import lombok.Getter;

@Getter
public class PrincipalDetail implements UserDetails {

	private Users users;
	private ArrayList<GrantedAuthority> authorities;

	public PrincipalDetail(Users users, ArrayList<GrantedAuthority> authorities) {
		this.users = users;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		return users.getUserId();
	}

	public String getUserName() {
		return users.getUserName();
	}

//	계정이 만료되지 않았는지 리턴 (true : 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

//	계정이 잠겨있지 않았는지 리턴 (true : 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

//	비밀번호가 만료되지 않았는지 리턴 (true : 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

//	계정이 활성화인지 리턴 (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

}
