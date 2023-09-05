package com.springboot.limitLoginAttempts.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface newUserRepository extends CrudRepository<newUser, Integer>{
	
	@Query("update newUser set failed_attempt = ?1 where email = ?2")
	@Modifying
	public void updateFailAttempt(int failAttemps, String email);
	
	@Query("select u from newUser u where u.email = :username")
	public newUser getUserbyUername(@Param("username") String username);
}
