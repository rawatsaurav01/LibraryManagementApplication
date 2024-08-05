package com.library.authorisation.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service
public class JWTGeneratorService {

	@Value("${jwt.secret.key}")
	private String secretKey;
	
	public static final long VALIDITY_PERIOD=60*60*1000;
	
	public String generateToken(String email) {
		return Jwts.builder()
				.setExpiration(new Date(System.currentTimeMillis()+VALIDITY_PERIOD))
				.setIssuedAt(new Date())
				.setSubject(email)
				.setIssuer("com.library")
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}
}
