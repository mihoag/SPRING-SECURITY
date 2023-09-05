package com.springboot.limitLoginAttempts.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.limitLoginAttempts.handler.failureHandler;
import com.springboot.limitLoginAttempts.handler.successhandler;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity

public class websecurityconfig extends WebSecurityConfigurerAdapter {
  @Bean
  public BCryptPasswordEncoder passwordEncoder()
  {
	  return new BCryptPasswordEncoder();
  }
  
  @Bean
  public UserDetailsService detailservice()
  {
	  return new newUserDetailService();
  }
  
  @Bean
  public DaoAuthenticationProvider provider()
  {
	  DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
	  auth.setPasswordEncoder(passwordEncoder());
	  auth.setUserDetailsService(detailservice());
	  return auth;
  }

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	// TODO Auto-generated method stub
	//super.configure(auth);
	auth.authenticationProvider(provider());
}

@Override
protected void configure(HttpSecurity http) throws Exception {
	// TODO Auto-generated method stub
	//super.configure(http);
	http
	.authorizeRequests()
	.anyRequest().authenticated()
	.and()
	.formLogin()
	  .loginPage("/login")
	  .usernameParameter("email")
      .passwordParameter("pass")
      .failureHandler(failhandler)
      .successHandler(successhandler)
      .permitAll()
    .and()
    .logout().permitAll();
}

@Autowired
private failureHandler failhandler;

@Autowired
private successhandler successhandler;

}
