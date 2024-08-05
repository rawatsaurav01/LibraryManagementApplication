package com.library.wishlist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.wishlist.entity.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{

	Optional<Wishlist> findByWishlistId(int wishlistId);
	List<Wishlist> findByEmailId(String emailId);

	boolean existsByTitleAndEmailId(String title,String emailId);
	
}
