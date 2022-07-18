package repository;

import domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
}
// MongoRepository database'e bağlanabilmesi için.