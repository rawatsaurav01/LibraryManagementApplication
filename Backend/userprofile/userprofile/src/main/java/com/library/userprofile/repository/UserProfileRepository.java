package com.library.userprofile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.userprofile.entity.User;

@Repository
public interface UserProfileRepository extends JpaRepository<User,Integer> {

	boolean existsByEmail(String email);
	
	boolean findByEmail(String email);
	
	@Query("Select u from User u where u.email=?1")
	Optional<User> getUserByEmail(String email);

	
	@Query("Select u from User u where u.userId=?1")
	Optional<User> getUserByUserId(int userId);
}
