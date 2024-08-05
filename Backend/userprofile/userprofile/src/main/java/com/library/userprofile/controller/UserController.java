package com.library.userprofile.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.userprofile.Exceptions.UserDoesNotExistException;
import com.library.userprofile.Exceptions.UserExistsException;
import com.library.userprofile.entity.User;
//import com.library.userprofile.entity.UserCredentials;
import com.library.userprofile.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User newUser) throws UserExistsException{
		User registerUser = this.userService.registerUser(newUser);
		return new ResponseEntity<>(registerUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/{email}")
	public Optional<User> getUserByEmail(@PathVariable("email") String email) throws UserDoesNotExistException {
		return this.userService.getUserByEmail(email);
	}
	
	@GetMapping("/id/{userId}")
	public Optional<User> getUserById(@PathVariable("userId") int userId) throws UserDoesNotExistException {
		return this.userService.getUserByUserId(userId);
	}
	
//	@PostMapping("/login")
//	public ResponseEntity<?> loginUser(@RequestBody UserCredentials credentials){
//		Map<String, String> token = this.userService.authenticateUser(credentials);
//		return new ResponseEntity<>(token,HttpStatus.OK);
//		
//	}
	
	

}
