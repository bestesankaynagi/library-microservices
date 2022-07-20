package com.innova.internship.jwtsupport;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Map;

public class JwtUtils {
	private static final String BEARER_TYPE = "Bearer ";

	private JwtUtils() {
	}

	public static Map<String, Claim> retrieveClaims(String token) {
		return decode(token).getClaims();
	}

	public static DecodedJWT decode(String token) {
		return JWT.decode(token);
	}

	public static DecodedJWT decodeHeader(String authorizationHeader) {
		if (authorizationHeader == null) return null;
		return JWT.decode(authorizationHeader.substring(BEARER_TYPE.length()));
	}
}
