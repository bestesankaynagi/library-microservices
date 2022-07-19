package tr.com.innova.internship.userservice.repository;

import tr.com.innova.internship.userservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
