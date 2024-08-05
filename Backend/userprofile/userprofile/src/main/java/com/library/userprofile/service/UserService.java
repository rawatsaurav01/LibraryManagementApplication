package com.library.userprofile.service;


import java.util.Optional;

import com.library.userprofile.Exceptions.UserDoesNotExistException;
import com.library.userprofile.Exceptions.UserExistsException;
import com.library.userprofile.entity.User;
//import com.library.userprofile.entity.UserCredentials;

public interface UserService {
	
	User registerUser(User newUser) throws UserExistsException;
//	Map<String,String> authenticateUser(UserCredentials credentials);
	
	Optional<User> getUserByEmail(String email) throws UserDoesNotExistException;

	Optional<User> getUserByUserId(int userId) throws UserDoesNotExistException;

}
