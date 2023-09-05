package com.springboot.limitLoginAttempts.user;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class newUserService {

public static final int MAX_FAILED_ATTEMPTS = 3;
private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours
	 
	@Autowired
	private newUserRepository rs;
	
	public void increaseFailedAttemps(newUser u)
	{
		int num = u.getFailedAttempt();
		rs.updateFailAttempt(num+1, u.getEmail());
	}
	public void  resetFailAttempt(String email)
	{
		rs.updateFailAttempt(0, email);
		newUser u = rs.getUserbyUername(email);
		u.setAccountNonLocked(true);
		u.setLockTime(null);
		rs.save(u);	
	}
	public void lock(newUser u)
	{
		u.setAccountNonLocked(false);
		u.setLockTime(new Date());
		rs.save(u);
	}
	public boolean unlockWhenTimeExpired(newUser u)
	{
	   	long lockTimeInMillis = u.getLockTime().getTime();
	   	long currentTimeinMillis = System.currentTimeMillis();
	   	
	   	if(lockTimeInMillis + LOCK_TIME_DURATION < currentTimeinMillis)
	   	{
	   		u.setAccountNonLocked(true);
	   		u.setFailedAttempt(0);
	   		u.setLockTime(null);
	   		
	   		rs.save(u);
	   		return true;
	   	}
	   	return false;
	}
	
	
}
