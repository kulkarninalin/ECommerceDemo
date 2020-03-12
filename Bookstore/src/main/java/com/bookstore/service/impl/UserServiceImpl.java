package com.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.domain.User;
import com.bookstore.domain.security.PasswordToken;
import com.bookstore.repository.PassworResetTokenRepository;
import com.bookstore.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private PassworResetTokenRepository passworResetTokenRepository;

	@Override
	public PasswordToken getPasswordToken(String token) {
		// TODO Auto-generated method stub
		return passworResetTokenRepository.findByToken(token);
	}

	@Override
	public void creatPAsswordResetToken(User user, String token) {
		final PasswordToken myToken=new PasswordToken(token, user);
		passworResetTokenRepository.save(myToken);
		
	}

}
