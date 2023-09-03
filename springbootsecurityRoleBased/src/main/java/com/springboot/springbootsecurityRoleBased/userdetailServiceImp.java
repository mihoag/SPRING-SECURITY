package com.springboot.springbootsecurityRoleBased;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class userdetailServiceImp implements UserDetailsService {

	@Autowired
	private UserRepository ur;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		user u = ur.getUserbyUsername(username);
		if(u == null)
		{
			throw new UsernameNotFoundException("Not found !!");
		}
		return new MyUserDetail(u);
	}

}
