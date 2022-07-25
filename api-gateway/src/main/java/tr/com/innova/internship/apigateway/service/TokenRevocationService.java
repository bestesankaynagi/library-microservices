package tr.com.innova.internship.apigateway.service;


import org.springframework.stereotype.Service;
import tr.com.innova.internship.apigateway.domain.RevokedToken;
import tr.com.innova.internship.apigateway.repository.RevokedTokenRepository;

import java.time.Instant;
import java.util.Date;


public class TokenRevocationService {
    private final RevokedTokenRepository repository;

    public TokenRevocationService(RevokedTokenRepository repository) {
        this.repository = repository;
    }

    public void revokeToken(String refreshTokenId) {
        RevokedToken revokedToken = new RevokedToken(refreshTokenId, Date.from(Instant.now()));

        repository.save(revokedToken);
    }

    public boolean isRevoked(String refreshTokenId) {
        return repository.findById(refreshTokenId).isPresent();
    }
}
