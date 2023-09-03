package com.springboot.loginwithGoogle.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<user, Long>{
 @Query("select u from user u where u.username = :username")
 public user getUserbyUername(@Param("username") String username);
}
