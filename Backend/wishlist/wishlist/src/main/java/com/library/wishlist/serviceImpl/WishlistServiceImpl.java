package com.library.wishlist.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.wishlist.entity.Wishlist;
import com.library.wishlist.exception.ResourceAlreadyExistsException;
import com.library.wishlist.exception.ResourceNotFoundException;
import com.library.wishlist.repository.WishlistRepository;
import com.library.wishlist.service.WishlistService;


@Service
public class WishlistServiceImpl implements WishlistService{
	
		@Autowired
		private WishlistRepository wishlistRepo;
		
		
		@Override
//		@Cacheable(value="newsCache",key="#emailId")
		public ResponseEntity<?> getWishlistByEmailId(String emailId) throws ResourceNotFoundException {
			List<Wishlist> wishlist = wishlistRepo.findByEmailId(emailId);
			if (wishlist.isEmpty()) {
				throw new ResourceNotFoundException("Item with given id not found!");
			}
			return new ResponseEntity<>(wishlist, HttpStatus.OK);
		}
	 
		@Override
		public ResponseEntity<?> addBooksToWishlist(Wishlist wishlist) throws ResourceAlreadyExistsException {
			if (wishlistRepo.existsByTitleAndEmailId(wishlist.getTitle(),wishlist.getEmailId())) {
				throw new ResourceAlreadyExistsException("Item in wishlist already exists!");
			}
//			clearNewsCache();
			return new ResponseEntity<>(wishlistRepo.save(wishlist), HttpStatus.CREATED);
	 
		}
	 
		@Override
//		@CacheEvict(cacheNames = "newsCache", key = "#wishlistId")
		public ResponseEntity<?> deleteFromWishlist(int wishlistId) throws ResourceNotFoundException {
			Optional<Wishlist> wishlist = wishlistRepo.findByWishlistId(wishlistId);
			if (wishlist.isEmpty()) {
				throw new ResourceNotFoundException("Item with given id not found");
			}
			wishlistRepo.deleteById(wishlistId);
			return new ResponseEntity<>("Item deleted successfully!", HttpStatus.OK);
		}
	 
//		@CacheEvict(cacheNames = "newsCache", allEntries = true)
//		public void clearNewsCache() {
//	 
//		}
}
