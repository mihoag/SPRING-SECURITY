package com.springboot.loginwithGithub.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {
  @Autowired
  private UserRepository repo;
  
  public void processOAuthPostLogin(String username)
  {
	  user u = repo.getUserbyUername(username);
	  if(u == null)
	  {
		  user uer = new user();
		  uer.setUsername(username);
		  uer.setEnabled(1);
		  uer.setProvider(Provider.GITHUB);
		  repo.save(uer);
	  }
  }
}
