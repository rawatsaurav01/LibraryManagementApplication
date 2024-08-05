package com.library.authorisation.service;

import java.util.Map;



import com.library.authorisation.entity.UserCredentials;

public interface AuthorisationService {
	Map<String,String> authenticateUser(UserCredentials credentials);
}
