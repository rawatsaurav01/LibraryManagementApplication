package com.library.wishlist.ServiceImplTest;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.library.wishlist.entity.Wishlist;
import com.library.wishlist.exception.ResourceAlreadyExistsException;
import com.library.wishlist.exception.ResourceNotFoundException;
import com.library.wishlist.repository.WishlistRepository;
import com.library.wishlist.serviceImpl.WishlistServiceImpl;

@ExtendWith(MockitoExtension.class)
class WishlistServiceImplTest {
//
//	@Mock
//	private WishlistRepository wishlistRepository;
//
//	@InjectMocks
//	private WishlistServiceImpl wishlistService;
//
//	@Test
//
//	void testGetWishlistByEmailId() throws ResourceNotFoundException {
//
//		// Mock data
//		String emailId = "test@example.com";
//		List<Wishlist> wishlist = new ArrayList<>();
//		wishlist.add(new Wishlist());
//		// Mock behavior
//		when(wishlistRepository.findByEmailId(emailId)).thenReturn(wishlist);
//		// Call the method
//		ResponseEntity<?> result = wishlistService.getWishlistByEmailId(emailId);
//		// Verify the result
//		assertEquals(HttpStatus.OK, result.getStatusCode());
//		assertEquals(wishlist, result.getBody());
//		// Verify that the repository method was called with the correct parameters
//		verify(wishlistRepository).findByEmailId(emailId);
//	}
//
//	@Test
//	void testAddBooksToWishlist() throws ResourceAlreadyExistsException {
//
//		// Mock data
//		Wishlist wishlist = new Wishlist();
//		wishlist.setTitle("Book Title");
//		// Mock behavior
//		when(wishlistRepository.existsByTitleAndEmailId(wishlist.getTitle(),wishlist.getEmailId())).thenReturn(false);
//		when(wishlistRepository.save(wishlist)).thenReturn(wishlist);
//		// Call the method
//		ResponseEntity<?> result = wishlistService.addBooksToWishlist(wishlist);
//		// Verify the result
//		assertEquals(HttpStatus.CREATED, result.getStatusCode());
//		assertEquals(wishlist, result.getBody());
//		// Verify that the repository method was called with the correct parameters
//		verify(wishlistRepository).existsByTitleAndEmailId(wishlist.getTitle(),wishlist.getEmailId());
//		verify(wishlistRepository).save(wishlist);
//	}
//
//	@Test
//	void testDeleteFromWishlist() throws ResourceNotFoundException {
//
//		// Mock data
//		int wishlistId = 1;
//		Wishlist wishlist = new Wishlist();
//		wishlist.setWishlistId(wishlistId);
//		// Mock behavior
//		when(wishlistRepository.findByWishlistId(wishlistId)).thenReturn(Optional.of(wishlist));
//		// Call the method
//		ResponseEntity<?> result = wishlistService.deleteFromWishlist(wishlistId);
//		// Verify the result
//		assertEquals(HttpStatus.OK, result.getStatusCode());
//		assertEquals("Item deleted successfully!", result.getBody());
//
//		// Verify that the repository method was called with the correct parameters
//
//		verify(wishlistRepository).findByWishlistId(wishlistId);
//
//		verify(wishlistRepository).deleteById(wishlistId);
//
//	}

}
