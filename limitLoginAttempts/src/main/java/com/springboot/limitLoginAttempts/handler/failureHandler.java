package com.springboot.limitLoginAttempts.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.springboot.limitLoginAttempts.user.newUser;
import com.springboot.limitLoginAttempts.user.newUserRepository;
import com.springboot.limitLoginAttempts.user.newUserService;

@Component
public class failureHandler extends SimpleUrlAuthenticationFailureHandler{

	@Autowired
	private newUserService ser;
	
	@Autowired
	private newUserRepository rs;
	
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		newUser u = rs.getUserbyUername(email);
		
		String msg = new String();
		if(u != null)
		{
			if(u.isEnabled() && u.isAccountNonLocked())
			{
				if(u.getFailedAttempt() < ser.MAX_FAILED_ATTEMPTS)
				{
				//	System.out.println(u.getFailedAttempt());
					ser.increaseFailedAttemps(u);
				}
				else
				{
				  ser.lock(u);
				  System.out.println("Your account has been locked due to 3 failed attempts."
                          + " It will be unlocked after 24 hours.");
				  exception = new LockedException("Your account has been locked due to 3 failed attempts."
                          + " It will be unlocked after 24 hours.");
				 msg= "Your account has been locked due to 3 failed attempts."
                          + " It will be unlocked after 24 hours.";
              }
		    }
			else if(!u.isAccountNonLocked())
			{
				System.out.println("Your account has been locked due to 3 failed attempts."
                          + " It will be unlocked after 24 hours.");
				if(ser.unlockWhenTimeExpired(u))
				{
					exception = new LockedException("Your account has been unlocked. Please try to login again.");
					msg= "Your account has been unlocked. Please try to login again.";
					
				}
			}
			
		}
		
		//System.out.println("Error");
		//System.out.println(request.getRequestURI());
		//super.setDefaultFailureUrl("/login?msg=" +msg);
		//System.out.println(request.getMethod());
		//super.setDefaultFailureUrl("/login1");
		//System.out.println("err");
	   //response.sendRedirect("/login1?msg=" + msg);
		super.setDefaultFailureUrl("/login?error");
		super.onAuthenticationFailure(request, response, exception);
	}

}
