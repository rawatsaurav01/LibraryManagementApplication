//package com.library.wishlist.controller;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//import static org.mockito.Mockito.*;
//
//
//
//import static org.mockito.Mockito.when;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
////import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//
//
//
//import com.library.wishlist.entity.Wishlist;
//import com.library.wishlist.exception.ExternalServiceException;
//import com.library.wishlist.exception.ResourceNotFoundException;
//import com.library.wishlist.service.WishlistService;
//
//
//
//@ExtendWith(MockitoExtension.class)
//class WishlistControllerTest {
//
//
//
//  @Mock
//  private WishlistService wishlistService;
//
//
//
//  @InjectMocks
//  private WishlistController wishlistController;
//
//
//
//  @Test
//  void testGetWishlistByEmailId() throws ExternalServiceException, ResourceNotFoundException {
//    // Mock data
//    String emailId = "test@example.com";
//    String token = "dummyToken";
//    Wishlist wishlist = new Wishlist();
//    // Mock behavior  .thenReturn(new ResponseEntity<>(wishlist, HttpStatus.OK));
//    when(wishlistService.getWishlistByEmailId(emailId)).thenReturn(new ResponseEntity<?>(wishlist, HttpStatus.OK));
//    // Call the method
//
//    ResponseEntity<?> result = wishlistController.getWishlistByEmailId(emailId, token);
//
//
//
//    // Verify the result
//
//    assertEquals(HttpStatus.OK, result.getStatusCode());
//    assertEquals(wishlist, result.getBody());
//
//
//    // Verify that the service method was called with the correct parameters
//    verify(wishlistService).getWishlistByEmailId(emailId);
//
//  }
//
//
//
//  @Test
//  void testAddBooksToWishlist() throws ExternalServiceException {
//    // Mock data
//    String token = "dummyToken";
//    Wishlist wishlist = new Wishlist();
//
//    // Mock behavior
//    when(wishlistService.addBooksToWishlist(wishlist)).thenReturn(wishlist);
//    // Call the method
//    ResponseEntity<?> result = wishlistController.addBooksToWishlist(wishlist, token);
//    // Verify the result
//
//    assertEquals(HttpStatus.OK, result.getStatusCode());
//    assertEquals(wishlist, result.getBody());
//
//    // Verify that the service method was called with the correct parameters
//    verify(wishlistService).addBooksToWishlist(wishlist);
//  }
//
//
//
//  @Test
//  void testDeleteFromWishlist() throws ExternalServiceException {
//    // Mock data
//    int wishlistId = 1;
//    String token = "dummyToken";
//
//    // Mock behavior
//    when(wishlistService.deleteFromWishlist(wishlistId)).thenReturn("Item deleted successfully");
//
//    // Call the method
//
//    ResponseEntity<?> result = wishlistController.deleteFromWishList(wishlistId, token);
//
//    // Verify the result
//
//    assertEquals(HttpStatus.OK, result.getStatusCode());
//
//    assertEquals("Item deleted successfully", result.getBody());
//
//    // Verify that the service method was called with the correct parameters
//
//    verify(wishlistService).deleteFromWishlist(wishlistId);
//
//  }
//
//}
//
