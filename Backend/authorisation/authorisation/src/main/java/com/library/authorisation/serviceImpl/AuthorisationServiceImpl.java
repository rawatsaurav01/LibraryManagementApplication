package com.library.authorisation.serviceImpl;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.authorisation.entity.User;
import com.library.authorisation.entity.UserCredentials;
import com.library.authorisation.externalservice.UserService;
import com.library.authorisation.service.AuthorisationService;
import com.library.authorisation.service.JWTGeneratorService;

@Service
public class AuthorisationServiceImpl implements AuthorisationService{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTGeneratorService jwtGeneratorService;
	
	@Override
	public Map<String, String> authenticateUser(UserCredentials credentials) {
		
		
		Optional<User> userByEmail = userService.getUserByEmail(credentials.getEmail());
		if(userByEmail.isEmpty()) {
			throw new RuntimeException("User not Found");
		}
		
		User user=userByEmail.get();
		if(user.getPassword().equals(credentials.getPassword())) {
			String token = jwtGeneratorService.generateToken(credentials.getEmail());
			return Map.of("jwt_token",token);
		}
		else {
			throw new RuntimeException("Credentials Mismatch");
		}
	}


}
