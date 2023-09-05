package com.springboot.limitLoginAttempts.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springboot.limitLoginAttempts.user.newUser;
import com.springboot.limitLoginAttempts.user.newUserRepository;

public class newUserDetailService implements UserDetailsService{

	@Autowired
	private newUserRepository rs;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//return null;
		newUser u = rs.getUserbyUername(username);
		return new newUserDetails(u);
		
	}

}
