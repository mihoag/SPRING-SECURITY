package com.springboot.springbootsecurityRoleBased;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class websecurityconfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource datasource;
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userdetailservice()
	{
		return new userdetailServiceImp();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authen = new DaoAuthenticationProvider();
		authen.setPasswordEncoder(passwordEncoder());
		authen.setUserDetailsService(userdetailservice());
		return authen;
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
		.antMatchers("/").permitAll()
		.antMatchers("/new").hasAnyAuthority("ADMIN","CREATOR")
		.antMatchers("/edit/*").hasAnyAuthority("ADMIN","EDITOR")
		.antMatchers("/delete/*").hasAnyAuthority("ADMIN")
		.anyRequest().authenticated()
		.and()
		.addFilterBefore(getCustomLoginFilter(), CustomLoginFilter.class)
		.formLogin().permitAll()
		.loginPage("/login")
		.usernameParameter("u")
		.passwordParameter("p")
		.loginProcessingUrl("/dologin")
		/*.defaultSuccessUrl("/successlogin", true)*/
		.successForwardUrl("/successlogin")
		.failureHandler(new AuthenticationFailureHandler() {
			
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				// TODO Auto-generated method stub
				System.out.println("Login fail");
				response.sendRedirect("/");
				
			}
		})
		.and()
		.logout().permitAll()
		.logoutUrl("/dologout")
		.logoutSuccessHandler(new LogoutSuccessHandler() {
			
			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException {
				// TODO Auto-generated method stub
				System.out.println("Logout successfully");
				response.sendRedirect("/successLogout");
			}
		})
		.and()
		/*.rememberMe().tokenValiditySeconds(3600)*?
		 *
		 */
		/*.rememberMe().tokenRepository(persistentTokenRepository())*/
		
		.exceptionHandling().accessDeniedPage("/403");
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository()
	{
		JdbcTokenRepositoryImpl tokenrepo = new JdbcTokenRepositoryImpl();
		tokenrepo.setDataSource(datasource);
		return tokenrepo;
	}
	
	private CustomLoginFilter getCustomLoginFilter() throws Exception
	{
		CustomLoginFilter filter = new CustomLoginFilter("/dologin", "POST");
		filter.setAuthenticationManager(authenticationManager());
		/*filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
			
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				// TODO Auto-generated method stub
				System.out.println("Fail");
				response.sendRedirect("login?error");
	
			}
		});*/
		return filter;
	}
}
