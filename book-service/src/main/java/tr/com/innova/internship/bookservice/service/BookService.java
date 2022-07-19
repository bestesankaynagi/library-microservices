package tr.com.innova.internship.bookservice.service;

import com.innova.internship.loggingsupport.rest.dto.BookDto;
import org.springframework.stereotype.Service;
import tr.com.innova.internship.bookservice.domain.Book;
import tr.com.innova.internship.bookservice.domain.BookMapper;
import tr.com.innova.internship.bookservice.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());

    }

    public BookDto saveBook(BookDto bookDto) {

        return bookMapper.toDto(bookRepository.save(this.bookMapper.toEntity(bookDto)));
    }


    public BookDto updateBook(BookDto bookDto) {
        Optional<Book> optionalBook = bookRepository.findById(bookDto.getId());
        if (optionalBook.isPresent()) {

            bookDto.setName("..");
            return bookMapper.toDto(bookRepository.save(this.bookMapper.toEntity(bookDto)));
        } else {
            throw new RuntimeException("No field found.");
        }
    }


    public void deleteById(String bookID) {
        bookRepository.deleteById(bookID);
    }

    public Book findById(String bookID) {
        Optional<Book> bookResponse = bookRepository.findById(bookID);
        return bookResponse.orElseThrow(() -> new RuntimeException("No record found for given id: " + bookID));

    }
}



