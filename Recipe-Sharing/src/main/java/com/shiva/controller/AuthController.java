package com.shiva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.config.JwtProvider;
import com.shiva.entity.User;
import com.shiva.repository.UserRepository;
import com.shiva.request.LoginRequest;
import com.shiva.response.AuthResponse;
import com.shiva.service.CustomUserDetailsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception{
		
		String email = user.getEmail();
		String password = user.getPassword();
		String fullName = user.getFullName();
		
		User isExistEmail = userRepository.findByEmail(email);
		
		if(isExistEmail != null) {
			throw new Exception("Email is alredy used with anether account");
		}
		
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFullName(fullName);
		
		User savedUser = userRepository.save(createdUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse response = new AuthResponse();
		response.setJwt(token);
		response.setMessage("signup success");
		
		return response;
		
	}
	
	@PostMapping("/signin")
	public AuthResponse signinHandler(@RequestBody LoginRequest loginRequest) {
		
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authentication = authenticat(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
        String token = jwtProvider.generateToken(authentication);
		
		AuthResponse response = new AuthResponse();
		response.setJwt(token);
		response.setMessage("signin success");
		
		return response;
	}

	private Authentication authenticat(String username, String password) {
		
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
		if(userDetails == null) {
			throw new BadCredentialsException("user not found");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword()) ) {
			throw new BadCredentialsException("invalid password");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
	}
	
}
