package com.springboot.springbootsecurityRoleBased;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<user, Long>{
   
	@Query("SELECT u FROM user u WHERE u.username = :username")
	public user getUserbyUsername(@Param("username") String username);
}
