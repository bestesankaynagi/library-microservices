package tr.com.innova.internship.bookservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.com.innova.internship.bookservice.domain.Book;

public interface BookRepository extends MongoRepository<Book, Integer> {
}
