package tr.com.innova.internship.apigateway.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.com.innova.internship.apigateway.domain.RevokedToken;


public interface RevokedTokenRepository extends MongoRepository<RevokedToken, String> {
}
