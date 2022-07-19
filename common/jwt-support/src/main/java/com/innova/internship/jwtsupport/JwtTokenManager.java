package com.innova.internship.jwtsupport;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.auth0.jwt.interfaces.Verification;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.Map;

public class JwtTokenManager implements TokenManager {
	private final RSAKeyProvider keyProvider;
	private final JWTVerifier refreshTokenVerification;

	public JwtTokenManager(RSAKeyProvider keyProvider) {
		this.keyProvider = keyProvider;

		String privateKeyId = keyProvider.getPrivateKeyId();
		RSAPrivateKey privateKey = keyProvider.getPrivateKey();
		RSAPublicKey publicKey = keyProvider.getPublicKeyById(privateKeyId);

		this.refreshTokenVerification = JWT.require(Algorithm.RSA256(publicKey, privateKey))
				.withIssuer(JwtConstants.ISSUER)
				.withAudience(JwtConstants.AUDIENCE_REFRESH)
				.build();
	}

	@Override
	public String generateToken(TokenParam param) {
		Date issuedAt = new Date();
		Date expiredAt = param.getExpiredAt();
		String[] audience = param.getAudience();
		Map<String, Object> claims = param.getClaimMap();

		JWTCreator.Builder builder = JWT
				.create()
				.withIssuer(JwtConstants.ISSUER)
				.withExpiresAt(expiredAt)
				.withAudience(audience)
				.withIssuedAt(issuedAt);

		claims.forEach((k, v) -> builder.withClaim(k, String.valueOf(v)));

		String privateKeyId = keyProvider.getPrivateKeyId();

		RSAPrivateKey privateKey = keyProvider.getPrivateKey();
		RSAPublicKey publicKey = keyProvider.getPublicKeyById(privateKeyId);

		return builder.sign(Algorithm.RSA256(publicKey, privateKey));
	}

	@Override
	public void verify(String token, boolean discardExpiry) {
		String privateKeyId = keyProvider.getPrivateKeyId();
		RSAPrivateKey privateKey = keyProvider.getPrivateKey();
		RSAPublicKey publicKey = keyProvider.getPublicKeyById(privateKeyId);

		Verification verification = JWT
				.require(Algorithm.RSA256(publicKey, privateKey))
				.withIssuer(JwtConstants.ISSUER)
				.withAudience(JwtConstants.AUDIENCE_ACCESS);

		if (discardExpiry) {
			verification.acceptExpiresAt(8640000L);
		}

		verification.build().verify(token);
	}

	@Override
	public DecodedJWT verifyRefreshToken(String token) {
		return refreshTokenVerification.verify(token);
	}
}
