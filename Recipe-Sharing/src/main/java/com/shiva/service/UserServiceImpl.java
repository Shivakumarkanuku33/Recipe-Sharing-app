package com.shiva.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiva.config.JwtProvider;
import com.shiva.entity.User;
import com.shiva.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User findUserById(Long userId) throws Exception {

		Optional<User> optional = userRepository.findById(userId);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new Exception("user not found with id "+ userId);
	}

	@Override
	public User findUserByJwt(String jwt) throws Exception {
		
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		
		if(email == null) {
			throw new Exception("provide valid jwt token...");
		}
		
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			throw new Exception("user not found with email "+email);
		}
		
		return user;
	}

}
