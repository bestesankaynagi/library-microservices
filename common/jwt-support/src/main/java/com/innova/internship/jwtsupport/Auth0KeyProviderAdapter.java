package com.innova.internship.jwtsupport;


import com.auth0.jwt.interfaces.RSAKeyProvider;
import tr.com.innova.internship.encryiption.AsymmetricEncryptionKeyProvider;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


public class Auth0KeyProviderAdapter implements RSAKeyProvider {
    private AsymmetricEncryptionKeyProvider<RSAPrivateKey, RSAPublicKey> asymmetricEncryptionKeyProvider;

    public Auth0KeyProviderAdapter(
            AsymmetricEncryptionKeyProvider<RSAPrivateKey, RSAPublicKey> asymmetricEncryptionKeyProvider) {
        this.asymmetricEncryptionKeyProvider = asymmetricEncryptionKeyProvider;
    }

    @Override
    public RSAPublicKey getPublicKeyById(String keyId) {
        return asymmetricEncryptionKeyProvider.getPublicKey();
    }

    @Override
    public RSAPrivateKey getPrivateKey() {
        return asymmetricEncryptionKeyProvider.getPrivateKey();
    }

    @Override
    public String getPrivateKeyId() {
        return null; // intentionally return null
    }
}
