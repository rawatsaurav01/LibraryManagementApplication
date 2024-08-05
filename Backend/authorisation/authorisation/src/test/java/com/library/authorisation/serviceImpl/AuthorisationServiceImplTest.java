package com.library.authorisation.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;



import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.library.authorisation.entity.User;
import com.library.authorisation.entity.UserCredentials;
import com.library.authorisation.externalservice.UserService;
import com.library.authorisation.service.JWTGeneratorService;

@ExtendWith(MockitoExtension.class)
class AuthorisationServiceImplTest {

  
	@Mock
    private UserService userService;

	@Mock
	private JWTGeneratorService jwtGeneratorService;

	@InjectMocks
	private AuthorisationServiceImpl authorisationService;

	@Test
	void testAuthenticateUser() {

    // Mock data
    UserCredentials credentials = new UserCredentials();
    credentials.setEmail("test@example.com");
    credentials.setPassword("password123");
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("password123");
    String jwtToken = "dummyToken";
    // Mock behavior
    when(userService.getUserByEmail(credentials.getEmail())).thenReturn(Optional.of(user));
    when(jwtGeneratorService.generateToken(credentials.getEmail())).thenReturn(jwtToken);
    // Call the method
    Map<String, String> result = authorisationService.authenticateUser(credentials);
    // Verify the result
    assertNotNull(result);
    assertEquals(jwtToken, result.get("jwt_token"));
    // Verify that the external service method was called with the correct parameters
    verify(userService).getUserByEmail(credentials.getEmail());
    verify(jwtGeneratorService).generateToken(credentials.getEmail());
  }



  @Test
  void testAuthenticateUserUserNotFound() {
    // Mock data
    UserCredentials credentials = new UserCredentials();
    credentials.setEmail("nonexistent@example.com");
    credentials.setPassword("password123");

    // Mock behavior
    when(userService.getUserByEmail(credentials.getEmail())).thenReturn(Optional.empty());

    // Call the method and expect an exception
    assertThrows(RuntimeException.class, () -> authorisationService.authenticateUser(credentials));


    // Verify that the external service method was called with the correct parameters
    verify(userService).getUserByEmail(credentials.getEmail());
    verifyNoMoreInteractions(jwtGeneratorService);
  }

  @Test
  void testAuthenticateUserCredentialsMismatch() {
    // Mock data

    UserCredentials credentials = new UserCredentials();
    credentials.setEmail("test@example.com");
    credentials.setPassword("incorrectPassword");
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("password123");
    // Mock behavior
    when(userService.getUserByEmail(credentials.getEmail())).thenReturn(Optional.of(user));
    // Call the method and expect an exception
    assertThrows(RuntimeException.class, () -> authorisationService.authenticateUser(credentials));
    // Verify that the external service method was called with the correct parameters
    verify(userService).getUserByEmail(credentials.getEmail());
    verifyNoMoreInteractions(jwtGeneratorService);
  }

}


