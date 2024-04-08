package com.shiva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.entity.User;
import com.shiva.service.UserService;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/api/users/profile")
	public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		return user;
		
	}
	

//	@PostMapping("/createUsers")
//	public User createUser(@RequestBody User user) throws Exception {
//		User isExist = repository.findByEmail(user.getEmail());
//		if(isExist != null) {
//			throw new Exception("user is exist with" + user.getEmail());
//		}
//		User savedUser = repository.save(user);
//		return savedUser;
//}
//	@GetMapping("/users")
//	public List<User> getAllUsers() throws Exception{
//		List<User> users = repository.findAll();
//		return users;
//	}
//
//
//	@DeleteMapping("/users/{userId}")
//	public String deleteUser(@PathVariable Long userId) throws Exception {
//
//		repository.deleteById(userId);
//		return "User Deleted Successfully";
//
//}
}
