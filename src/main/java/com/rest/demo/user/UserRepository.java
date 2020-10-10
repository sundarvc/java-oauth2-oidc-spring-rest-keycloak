package com.rest.demo.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, UUID> {
	 
	
	@Query("SELECT u FROM users u WHERE LOWER(u.username) = LOWER(:username)")
	User findByName(@Param("username") String username);

}
