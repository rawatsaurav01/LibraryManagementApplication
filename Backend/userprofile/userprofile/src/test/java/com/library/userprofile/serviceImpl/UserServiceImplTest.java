package com.library.userprofile.serviceImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.library.userprofile.Exceptions.UserDoesNotExistException;
import com.library.userprofile.Exceptions.UserExistsException;
import com.library.userprofile.entity.User;
import com.library.userprofile.repository.UserProfileRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  private UserProfileRepository userProfileRepository;

  @InjectMocks
  private UserServiceImpl userService;

  @Test
  void testRegisterUser() throws UserExistsException {
    // Mock data
    User newUser = new User();
    newUser.setEmail("test@example.com");
    // Mock behavior
    when(userProfileRepository.existsByEmail(newUser.getEmail())).thenReturn(false);
    when(userProfileRepository.save(newUser)).thenReturn(newUser);
    // Call the method
    User result = userService.registerUser(newUser);
    // Verify the result
    assertNotNull(result);
    assertEquals(newUser.getEmail(), result.getEmail());
    // Verify that the repository method was called with the correct parameters
    verify(userProfileRepository).existsByEmail(newUser.getEmail());
    verify(userProfileRepository).save(newUser);
  }



  @Test
  void testGetUserByEmail() throws UserDoesNotExistException {
    // Mock data
    String email = "test@example.com";
    User user = new User();
    user.setEmail(email);
    // Mock behavior
    when(userProfileRepository.getUserByEmail(email)).thenReturn(Optional.of(user));
    // Call the method
    Optional<User> result = userService.getUserByEmail(email);
    // Verify the result
    assertTrue(result.isPresent());
    assertEquals(email, result.get().getEmail());
    // Verify that the repository method was called with the correct parameters
    verify(userProfileRepository).getUserByEmail(email);
  }

  @Test
  void testGetUserByUserId() throws UserDoesNotExistException {
    // Mock data
    int userId = 123456;
    User user = new User();
    user.setUserId(userId);
    // Mock behavior
    when(userProfileRepository.getUserByUserId(userId)).thenReturn(Optional.of(user));
    // Call the method
    Optional<User> result = userService.getUserByUserId(userId);
    // Verify the result
    assertTrue(result.isPresent());
    assertEquals(userId, result.get().getUserId());
    // Verify that the repository method was called with the correct parameters
    verify(userProfileRepository).getUserByUserId(userId);
  }

}

