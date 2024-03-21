package org.jsp.ecommerceapp.controller;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.User;

import org.jsp.ecommerceapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")

public class UserController {

	@Autowired
	private UserService userservice;

	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return userservice.saveuser(user);
	}

	@PutMapping
	private ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User User) {
		return userservice.saveuser(User);
	}

	@GetMapping("/{id}")
	private ResponseEntity<ResponseStructure<User>> findByID(@PathVariable int id) {
		return userservice.findById(id);

	}

	@GetMapping("/name/{name}")
	private ResponseEntity<ResponseStructure<User>> findByName(@PathVariable String name) {
		return userservice.findByName(name);
	}

	@PostMapping("/verify-by-phone")
	private ResponseEntity<ResponseStructure<User>> verifyByPhone(@RequestParam long phone,
			@RequestParam String password) {
		return userservice.verifyByPhone(phone, password);
	}

	@PostMapping("/verify-by-email")
	private ResponseEntity<ResponseStructure<User>> verifyByEmail(@RequestParam String email,
			@RequestParam String password) {
		return userservice.verifyByEmail(email, password);
	}

}
