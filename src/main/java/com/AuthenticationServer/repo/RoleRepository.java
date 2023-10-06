package com.AuthenticationServer.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AuthenticationServer.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
  Optional<Role> findByName(String name);
  
}
