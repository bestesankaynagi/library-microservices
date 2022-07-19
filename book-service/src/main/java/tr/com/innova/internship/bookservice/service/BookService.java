package tr.com.innova.internship.bookservice.service;


import org.springframework.stereotype.Service;
import tr.com.innova.internship.bookservice.domain.BookMapper;
import tr.com.innova.internship.bookservice.repository.BookRepository;
import tr.com.innova.internship.commonrest.dto.BookDto;

import java.util.List;
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

    public void deleteById(String bookID) {
        bookRepository.deleteById(bookID);
    }

    public BookDto findById(String bookID) {

        return bookRepository.findById(bookID)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new RuntimeException("No record found for given id: " + bookID));
    }
}



