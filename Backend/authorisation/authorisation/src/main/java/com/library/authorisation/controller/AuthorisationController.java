package com.library.authorisation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.authorisation.entity.UserCredentials;
import com.library.authorisation.service.AuthorisationService;

@RestController
public class AuthorisationController {
	
	@Autowired
	AuthorisationService authorisationService;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserCredentials credentials){
		Map<String, String> token = this.authorisationService.authenticateUser(credentials);
		return new ResponseEntity<>(token,HttpStatus.OK);
		
	}

}
