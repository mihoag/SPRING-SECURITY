package com.springboot.sendOTP.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.sendOTP.CustomloginFilter;
import com.springboot.sendOTP.loginHandler.LoginFailureHandler;
import com.springboot.sendOTP.loginHandler.LoginSuccessHandle;
import com.springboot.sendOTP.user.UserService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public BCryptPasswordEncoder passwordencoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userSer()
	{
		return new MyUserServiceImp();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(passwordencoder());
		dao.setUserDetailsService(userSer());
		return dao;
	}
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);
		auth.authenticationProvider(authenticationProvider());
		
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		http
		.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.addFilterBefore(beforeLoginAuthentication, CustomloginFilter.class)

		.formLogin()
		.loginPage("/login")
		.usernameParameter("email")
		.passwordParameter("pass")
		.successHandler(loginsuccessHandler)
		.failureHandler(loginFailerHandler)
		.permitAll();
	}
	
	@Autowired
	private LoginSuccessHandle loginsuccessHandler;
	
	@Autowired
	private LoginFailureHandler loginFailerHandler;
	
	
	@Autowired
    private CustomloginFilter beforeLoginAuthentication;
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	    
       
}
