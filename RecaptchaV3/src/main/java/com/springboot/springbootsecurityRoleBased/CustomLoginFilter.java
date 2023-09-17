package com.springboot.springbootsecurityRoleBased;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Lớp này để thực hiện lọc username, password 
public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter{
      public CustomLoginFilter(String loginURL, String httpMethod)
      {
    	  setUsernameParameter("u");
    	  setPasswordParameter("p");
    	  super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(loginURL,httpMethod));
      }

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
	    System.out.println("Before processing authentication");
		String recaptchaResponse = request.getParameter("g-recaptcha-response");
		reCaptchaV3Handler r = new reCaptchaV3Handler();
		try {
			float score = r.verify(recaptchaResponse);
			if(score < 0.5)
			{
				request.getRequestDispatcher("otp_login").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		} 
		
		return super.attemptAuthentication(request, response);
	}
      
}
