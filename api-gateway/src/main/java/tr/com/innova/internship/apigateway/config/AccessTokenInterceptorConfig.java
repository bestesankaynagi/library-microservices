package tr.com.innova.internship.apigateway.config;

import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.innova.internship.jwtsupport.Auth0KeyProviderAdapter;
import com.innova.internship.jwtsupport.JwtTokenManager;
import com.innova.internship.jwtsupport.TokenManager;
import com.innova.internship.jwtsupport.TokenVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import tr.com.innova.internship.apigateway.interceptor.AccessTokenHandlerInterceptor;
import tr.com.innova.internship.apigateway.repository.RevokedTokenRepository;
import tr.com.innova.internship.apigateway.service.TokenRevocationService;
import tr.com.innova.internship.encryiption.AsymmetricEncryptionKeyProvider;
import tr.com.innova.internship.encryiption.SpringResourceAwareRSAKeyProvider;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class AccessTokenInterceptorConfig {

	@Value("${internship.security.token-service.private-key-resource-uri}")
	Resource privateKeyResource;
	@Value("${internship.security.token-service.public-key-resource-uri}")
	Resource publicKeyResource;

	@Bean
	AsymmetricEncryptionKeyProvider<RSAPrivateKey, RSAPublicKey> asymmetricEncryptionKeyProvider() {
		return new SpringResourceAwareRSAKeyProvider(
				publicKeyResource,
				privateKeyResource
		);
	}

	@Bean
	RSAKeyProvider keyProvider(AsymmetricEncryptionKeyProvider<RSAPrivateKey, RSAPublicKey> asymmetricEncryptionKeyProvider) {
		return new Auth0KeyProviderAdapter(asymmetricEncryptionKeyProvider);
	}

	@Bean
	TokenVerifier tokenVerifier(TokenManager tokenManager) {
		return tokenManager;
	}

	@Bean
	TokenManager tokenManager(RSAKeyProvider keyProvider) {
		return new JwtTokenManager(keyProvider);
	}

	@Bean
	TokenRevocationService tokenRevocationService(RevokedTokenRepository revokedTokenRepository) {
		return new TokenRevocationService(revokedTokenRepository);
	}

	@Bean
	AccessTokenHandlerInterceptor accessTokenHandlerInterceptor(
			TokenVerifier tokenVerifier,
			TokenRevocationService tokenRevocationService
	) {
		return new AccessTokenHandlerInterceptor(tokenVerifier, tokenRevocationService);
	}

}
