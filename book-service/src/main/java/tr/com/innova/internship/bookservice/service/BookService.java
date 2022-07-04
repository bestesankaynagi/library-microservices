package tr.com.innova.internship.bookservice.service;

import org.springframework.stereotype.Service;
import tr.com.innova.internship.bookservice.domain.Book;
import tr.com.innova.internship.bookservice.domain.BookMapper;
import tr.com.innova.internship.bookservice.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    //todo ObjectMapper ile Book objesinden BookDto dönüştürmek gerekiyor
    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
        // bookMapper.toDto() kullanarak List<BookDto> tipinde obje döndüreceğiz
    }

    public Book   createBook(Book books) {
        // bookMapper.toEntity() kullanarak Book objesine çevireceğiz ve kaydedeceğiz.
        return bookRepository.save(books);
    }

}
