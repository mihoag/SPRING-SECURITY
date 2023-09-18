package com.springboot.sendOTP;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.springboot.sendOTP.recaptcha.RecaptchaHandler;
import com.springboot.sendOTP.user.UserService;
import com.springboot.sendOTP.user.users;


@Component
public class CustomloginFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	private UserService ser ;
	
	  
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authManager) {
        super.setAuthenticationManager(authManager);
    }
	
	
	    @Autowired
	    @Override
	    public void setAuthenticationFailureHandler(
	            AuthenticationFailureHandler failureHandler) {
	        super.setAuthenticationFailureHandler(failureHandler);
	    }
	     
	    @Autowired
	    @Override
	    public void setAuthenticationSuccessHandler(
	            AuthenticationSuccessHandler successHandler) {
	        super.setAuthenticationSuccessHandler(successHandler);
	    }
	
	public CustomloginFilter() {
		setUsernameParameter("email");
		setPasswordParameter("pass");
		
		super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		System.out.println(email);
	    users u = ser.selectByEmail(email);
	    
	    if(u != null)
	    {
	    	if(u.isOTPrequired())
	    	{
	    		return super.attemptAuthentication(request, response);
	    	}
	    	  System.out.println("attemptAuthentication - email: " + email);
	    	 String respo = request.getParameter("g-recaptcha-response");
	  		RecaptchaHandler re = new RecaptchaHandler();
	  		System.out.println(respo);
	  		float score = re.verify(respo);
	  		
	  		if(score < 0.5)
	  		{
	  			try {
					ser.generateOneTimePassword(u);
					throw new InsufficientAuthenticationException("OTP");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	  			
	  		}
	    }
	
		//System.out.println("Before login authentication");
		
		
		//System.out.println(res);
		
		//System.out.println(score);
		
		
		return super.attemptAuthentication(request, response);
	}
}
