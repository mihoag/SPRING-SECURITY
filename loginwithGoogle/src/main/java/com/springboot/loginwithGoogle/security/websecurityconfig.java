package com.springboot.loginwithGoogle.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.springboot.loginwithGoogle.security.oauth.CustomOAuth2User;
import com.springboot.loginwithGoogle.security.oauth.CustomOauth2UserService;
import com.springboot.loginwithGoogle.user.userService;


@Configuration
@EnableWebSecurity
public class websecurityconfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userdetail()
	{
		return new UserDetailServiceImp();
	}
	
	@Bean
	public DaoAuthenticationProvider AuthenticationProvider()
	{
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setPasswordEncoder(passwordEncoder());
		auth.setUserDetailsService(userdetail());
		
		return auth;
	}
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);
		auth.authenticationProvider(AuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		http
		.authorizeRequests()
		.antMatchers("/login").permitAll()
		.anyRequest().authenticated()
		.and()
	     .formLogin()
	       .loginPage("/login")
	       .usernameParameter("email")
	       .passwordParameter("pass")
		   .defaultSuccessUrl("/")
	       .permitAll()
	    .and()
	    .oauth2Login()
	     .loginPage("/login")
	     .userInfoEndpoint().userService(aouthservice) 
	    .and()
	   .successHandler(new AuthenticationSuccessHandler() {
			
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				// TODO Auto-generated method stub
				CustomOAuth2User o = (CustomOAuth2User) authentication.getPrincipal();
				userservice.processOAuthPostLogin(o.getEmail());
				response.sendRedirect("/");	
			}
		})
	    .and()
	     .logout()
	       .logoutUrl("/dologout")
	       .logoutSuccessUrl("/successlogout")
	       .permitAll();
	}
	
	@Autowired
	private userService userservice;
	
	@Autowired
	private CustomOauth2UserService aouthservice;

}
