package com.shiva.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class HomeController {

	@GetMapping()
	public String homeController() {
		return "Welcome to spring-boot shiva";
	}
}
