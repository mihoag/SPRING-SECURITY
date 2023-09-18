package com.springboot.sendOTP.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.sendOTP.user.users;

public class MyuserDetail implements UserDetails {

    private users u;
    
    public MyuserDetail(users u)
    {
    	this.u  =u;
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		//return null;
		//SimpleGrantedAuthority auth = new SimpleGrantedAuthority(getPassword())
		return null;
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
		return u.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		//return false;
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		//return false;
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		//return false;
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		//return false;
		return true;
	}

	public users getU() {
		return u;
	}

	public void setU(users u) {
		this.u = u;
	}
	
	

}
