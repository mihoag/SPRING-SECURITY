package com.springboot.sendOTP.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface userRepository extends JpaRepository<users, Integer >{

	@Query("select u from users u where u.email = ?1")
	public users selectByEmail(String email);
}
