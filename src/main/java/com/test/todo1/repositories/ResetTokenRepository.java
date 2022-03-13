package com.test.todo1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.todo1.entities.ResetToken;

public interface ResetTokenRepository extends JpaRepository<ResetToken, Integer>{
	
	ResetToken findByToken(String token);

}
