package com.springboot.springbootsecurityRoleBased;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecuredPasswordGenerator {
public static void main(String[] args) {
	String rawpassword = "123456";
	BCryptPasswordEncoder b = new BCryptPasswordEncoder();
	//System.out.println(b.);
}
}
