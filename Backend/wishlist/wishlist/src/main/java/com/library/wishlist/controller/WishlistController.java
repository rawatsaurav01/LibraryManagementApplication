package com.library.wishlist.controller;



import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.wishlist.entity.Wishlist;
import com.library.wishlist.exception.ExternalServiceException;
import com.library.wishlist.service.WishlistService;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

	@Autowired
	private WishlistService wishlistService;

	Logger logger = LoggerFactory.getLogger(WishlistController.class);

	@GetMapping("/getbyemailid/{emailId}")
	public ResponseEntity<?> getWishlistByEmailId(@PathVariable String emailId,
			@Parameter(hidden = true) @RequestHeader("Authorization") String token) throws ExternalServiceException {
		try {
			ResponseEntity<?> wishlistByEmailId = wishlistService.getWishlistByEmailId(emailId);
			return new ResponseEntity<>(wishlistByEmailId, HttpStatus.OK);
		} catch (Exception e) {
			throw new ExternalServiceException(e.getMessage());
		}
	}

	@PostMapping("/additem")
	public ResponseEntity<?> addBooksToWishlist(@RequestBody Wishlist wishlist,
			@Parameter(hidden = true) @RequestHeader("Authorization") String token) throws ExternalServiceException {

		try {

			String payload = token.split("\\.")[1];
			String payloadString = new String(Base64.decodeBase64(payload), "UTF-8");
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(payloadString);

			String emailId = jsonNode.get("sub").asText();
			wishlist.setEmailId(emailId);
			return new ResponseEntity<>(wishlistService.addBooksToWishlist(wishlist), HttpStatus.OK);
		} catch (Exception e) {
			throw new ExternalServiceException(e.getMessage());
		}
	}

	@DeleteMapping("/delete/{wishlistId}")
	public ResponseEntity<?> deleteFromWishList(@PathVariable int wishlistId,

			@Parameter(hidden = true) @RequestHeader("Authorization") String token) throws ExternalServiceException {
		try {
			return new ResponseEntity<>(wishlistService.deleteFromWishlist(wishlistId), HttpStatus.OK);
		} catch (Exception e) {
			throw new ExternalServiceException(e.getMessage());
		}

	}
}
