package com.springboot.springbootsecurityRoleBased;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetail implements UserDetails {
     
	private user u;
	public MyUserDetail(user u)
	{
		this.u = u;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		Set<role> arr = u.getRoles();
		
		for(role r : arr)
		{
			authorities.add(new SimpleGrantedAuthority(r.getName()));
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return u.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
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
