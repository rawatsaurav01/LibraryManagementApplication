package com.library.authorisation.externalservice;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.library.authorisation.entity.User;

@FeignClient(name="USER-SERVICE")
public interface UserService {

	@GetMapping("/user/{email}")
	public Optional<User> getUserByEmail(@PathVariable("email") String email);
	
}
