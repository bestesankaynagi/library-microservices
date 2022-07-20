package com.innova.internship.jwtsupport;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface TokenVerifier {
	void verify(String token, boolean discardExpiry);

	DecodedJWT verifyRefreshToken(String token);
}
