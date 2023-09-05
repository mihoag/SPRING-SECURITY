package com.springboot.limitLoginAttempts.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.springboot.limitLoginAttempts.security.newUserDetails;
import com.springboot.limitLoginAttempts.user.newUser;
import com.springboot.limitLoginAttempts.user.newUserService;


@Component
public class successhandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private newUserService ser;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		newUserDetails u = (newUserDetails) authentication.getPrincipal();
		newUser nu = u.getNewUser();
		if(nu.getFailedAttempt() > 0)
		{
			ser.resetFailAttempt(u.getUsername());
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
