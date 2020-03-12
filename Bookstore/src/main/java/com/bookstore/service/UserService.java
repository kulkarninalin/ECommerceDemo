package com.bookstore.service;

import com.bookstore.domain.User;
import com.bookstore.domain.security.PasswordToken;

public interface UserService {
	
	PasswordToken getPasswordToken( final String token);

	void creatPAsswordResetToken(final User user,final String token);
}
