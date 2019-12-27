package com.alifoune.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alifoune.main.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmailAndPassword(String email, String password);

}
