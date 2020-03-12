package com.bookstore.repository;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bookstore.domain.User;
import com.bookstore.domain.security.PasswordToken;

public interface PassworResetTokenRepository extends JpaRepository<PasswordToken, Long>{

	
	PasswordToken findByToken(String token);
	
	PasswordToken findByUser(User user);
	
	Stream<PasswordToken> findAllByExpirtDateLessThan(Date now);
	
	@Modifying
	@Query("delete from PasswordToken t where t.expirydate <= ?1")
	void deleteAllExpiredSince(Date now);
}
