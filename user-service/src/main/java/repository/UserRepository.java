package repository;

import org.apache.tomcat.jni.Library;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <Library,String>{
}
// MongoRepository database'e bağlanabilmesi için.