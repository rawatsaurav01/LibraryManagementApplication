package com.library.wishlist.service;

import org.springframework.http.ResponseEntity;

import com.library.wishlist.entity.Wishlist;
import com.library.wishlist.exception.ResourceAlreadyExistsException;
import com.library.wishlist.exception.ResourceNotFoundException;

public interface WishlistService {

	
		public ResponseEntity<?> getWishlistByEmailId(String emailId) throws ResourceNotFoundException;
	 
		public ResponseEntity<?> addBooksToWishlist(Wishlist wishlist) throws ResourceAlreadyExistsException;

		public ResponseEntity<?> deleteFromWishlist(int id) throws ResourceNotFoundException;
	 
	
}
