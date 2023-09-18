package com.springboot.sendOTP.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.sendOTP.user.userRepository;
import com.springboot.sendOTP.user.users;

@Service
public class MyUserServiceImp implements UserDetailsService{

	@Autowired
	private userRepository res;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//return null;
		users u = res.selectByEmail(username);
		System.out.println(u.toString());
		
		if(u == null)
		{
			throw new UsernameNotFoundException("Users is not exist!!!");
		}
		return new MyuserDetail(u);
		
	}
	


}
