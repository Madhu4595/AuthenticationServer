package com.AuthenticationServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AuthenticationServer.entity.Users;


@Repository
public interface  UserRepository extends JpaRepository<Users, Long> {
	
	
		Users findByUsername(String username);

		Boolean existsByUsername(String username);

	  
}

