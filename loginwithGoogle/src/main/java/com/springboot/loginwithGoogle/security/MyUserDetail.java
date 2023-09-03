package com.springboot.loginwithGoogle.security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.loginwithGoogle.user.role;
import com.springboot.loginwithGoogle.user.user;


public class MyUserDetail implements UserDetails{

	private user u;
	
	public MyUserDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public MyUserDetail(user u) {
		//super();
		this.u = u;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		//return null;
		List<SimpleGrantedAuthority> arr = new ArrayList<>();
		Set<role> setRoles = u.getRoles();
		for(role r : setRoles)
		{
			arr.add(new SimpleGrantedAuthority(r.getName()));
		}
		return arr;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		//return null;
		return u.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		//return null;
		return u.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
