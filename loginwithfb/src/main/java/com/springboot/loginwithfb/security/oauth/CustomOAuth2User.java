package com.springboot.loginwithfb.security.oauth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {

	private OAuth2User oauth;

	public CustomOAuth2User(OAuth2User oauth) {
		//super();
		this.oauth = oauth;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return oauth.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return oauth.getAuthorities();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return oauth.getAttribute("name");
	}
	
	public String getEmail()
	{
		return oauth.<String>getAttribute("email");
	}
}
