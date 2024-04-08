package com.shiva.service;

import com.shiva.entity.User;

public interface UserService {

	public User findUserById(Long userId) throws Exception;
	
	public User findUserByJwt(String jwt) throws Exception;
}
