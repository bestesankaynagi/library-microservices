package tr.com.innova.internship.bookservice.service;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tr.com.innova.internship.bookservice.domain.Book;
import tr.com.innova.internship.bookservice.domain.BookMapper;
import tr.com.innova.internship.bookservice.repository.BookRepository;
import tr.com.innova.internship.commonrest.dto.BookDto;
import tr.com.innova.internship.commonrest.dto.RentDto;

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

    public void deleteById(String bookID) {
        bookRepository.deleteById(bookID);
    }

    public BookDto findById(String bookID) {

        return bookRepository.findById(bookID)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new RuntimeException("No record found for given id: " + bookID));
    }

    public BookDto rentBook(RentDto rentDto) {
        Optional<Book> optionalBook = bookRepository.findById(rentDto.getBookId());

        if (optionalBook.isEmpty()) throw new RuntimeException("No record found for given id: " + rentDto.getBookId());

        Book book = optionalBook.get();
        if (StringUtils.hasText(book.getTakenBy())) {
            throw new RuntimeException("The book is already taken by someone");
        }

        book.setTakenBy(rentDto.getTakenBy());
        book.setDueDate(rentDto.getDueDate());

        return bookMapper.toDto(bookRepository.save(book));
    }
}



