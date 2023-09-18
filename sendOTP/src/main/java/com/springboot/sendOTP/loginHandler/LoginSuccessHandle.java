package com.springboot.sendOTP.loginHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.springboot.sendOTP.security.MyuserDetail;
import com.springboot.sendOTP.user.UserService;
import com.springboot.sendOTP.user.userRepository;
import com.springboot.sendOTP.user.users;

@Component
public class LoginSuccessHandle extends SimpleUrlAuthenticationSuccessHandler{

	@Autowired
	private UserService ser;

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	   MyuserDetail userdetail = (MyuserDetail) authentication.getPrincipal();
		users u = userdetail.getU();
		if(u.isOTPrequired())
		{
			ser.clearOTP(u);
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}


}
