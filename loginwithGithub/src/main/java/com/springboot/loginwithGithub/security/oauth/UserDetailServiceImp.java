package com.springboot.loginwithGithub.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springboot.loginwithGithub.user.UserRepository;
import com.springboot.loginwithGithub.user.user;

public class UserDetailServiceImp implements UserDetailsService{

	@Autowired
	private UserRepository ur;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//return null;
		user u = ur.getUserbyUername(username);
		if(u == null)
		{
			throw new UsernameNotFoundException("Not fount username");
		}
		return new MyUserDetail(u);		
	}
}
