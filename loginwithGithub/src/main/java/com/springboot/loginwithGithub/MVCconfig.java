package com.springboot.loginwithGithub;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCconfig implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	// TODO Auto-generated method stub
    	//WebMvcConfigurer.super.addViewControllers(registry);
    	registry.addViewController("/login").setViewName("login");
    	registry.addViewController("/").setViewName("index");
    }
}
