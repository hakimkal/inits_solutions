package com.inits.expense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inits.expense.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);
	
	boolean existsByUsername(String username);
}
