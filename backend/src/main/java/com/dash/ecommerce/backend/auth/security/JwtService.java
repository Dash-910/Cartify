package com.dash.ecommerce.backend.auth.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.dash.ecommerce.backend.user.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private final SecretKey secretKey = Keys.hmacShaKeyFor("cartify-secret-key-for-jwt-authentication-12345789".getBytes());
	
	private final long expirationTime = 1000 * 60 * 60;
	
	public String generateToken(User user) {
		
		return  Jwts.builder()
				.subject(user.getEmail())
				.claim("userId", user.getId())
				.claim("role",user.getRole().name())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(secretKey)
				.compact();
	}
	
	public String extractEmail(String token) {
		
		return Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	public boolean isTokenValid(String token) {
		
		try {
			Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
