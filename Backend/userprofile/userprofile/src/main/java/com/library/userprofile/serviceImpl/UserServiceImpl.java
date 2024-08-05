package com.library.userprofile.serviceImpl;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.userprofile.Exceptions.UserDoesNotExistException;
import com.library.userprofile.Exceptions.UserExistsException;
import com.library.userprofile.entity.User;
//import com.library.userprofile.entity.UserCredentials;
import com.library.userprofile.repository.UserProfileRepository;
import com.library.userprofile.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
//	@Autowired
//	private JWTGeneratorService jwtGeneratorService;

	@Override
	public User registerUser(User newUser) throws UserExistsException {
		
		if(this.userProfileRepository.existsByEmail(newUser.getEmail())) {
			throw new UserExistsException("User with the email already exists");
		}
		return userProfileRepository.save(newUser);
	}
	
	@Override
	public Optional<User> getUserByEmail(String email) throws UserDoesNotExistException {
		Optional<User> user = userProfileRepository.getUserByEmail(email);
		if (user.isEmpty()) {
			throw new UserDoesNotExistException("User not found with email: " + email);
			}
		return user;
	}

	@Override
	public Optional<User> getUserByUserId(int userId) throws UserDoesNotExistException {
		Optional<User> user = userProfileRepository.getUserByUserId(userId);
		if (user.isEmpty()) {
			throw new UserDoesNotExistException("User with id: " + userId + " not found.");
			}
		return user;
	}


//	@Override
//	public Optional<User> getUserByEmail(String email) throws UserDoesNotExistException {
//		
//		if()
////		if(this.userProfileRepository.findByEmail(email)) {
////			throw new UserDoesNotExistException("User with the email does not exist");
////		}
//	
//		return Optional.of(this.userProfileRepository.getUserByEmail(email));
//		
//	}

//	@Override
//	public Map<String, String> authenticateUser(UserCredentials credentials) {
//		
//		Optional<User> userByEmail = userProfileRepository.getUserByEmail(credentials.getEmail());
//		if(userByEmail.isEmpty()) {
//			throw new RuntimeException("User not Found");
//		}
//		
//		User user=userByEmail.get();
//		if(user.getPassword().equals(credentials.getPassword())) {
////			if password matches then generate token using jwt
//			String token = jwtGeneratorService.generateToken(credentials.getEmail());
//			return Map.of("jwt_token",token);
//			
//		}
//		else {
//			throw new RuntimeException("Credentials Mismatch");
//		}
//	}

}
