package tr.com.innova.internship.authentication.config;

import com.innova.internship.jwtsupport.Auth0KeyProviderAdapter;
import com.innova.internship.jwtsupport.JwtTokenManager;
import com.innova.internship.jwtsupport.TokenManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import tr.com.innova.internship.encryiption.AsymmetricEncryptionKeyProvider;
import tr.com.innova.internship.encryiption.SpringResourceAwareRSAKeyProvider;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class TokenConfiguration {
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
    Auth0KeyProviderAdapter keyProvider(AsymmetricEncryptionKeyProvider<RSAPrivateKey, RSAPublicKey> asymmetricEncryptionKeyProvider) {
        return new Auth0KeyProviderAdapter(asymmetricEncryptionKeyProvider);
    }

    @Bean
    TokenManager tokenManager(Auth0KeyProviderAdapter provider) {
        return new JwtTokenManager(provider);
    }
}
