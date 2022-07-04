package tr.com.innova.internship.bookservice.controller;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import tr.com.innova.internship.bookservice.domain.Book;
import tr.com.innova.internship.bookservice.service.BookService;

import java.util.List;

@RestController
@Repository
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    private List<Book> getBooks() {
        return bookService.getAllBooks();
    }


    @PostMapping
    public Book createBook(@RequestBody Book book) {
        Book savedBook = bookService.createBook(book);
        return savedBook;
        /*public ResponseEntity<Book> createBook(@RequestBody Book book) {
            bookRepository.save(book);
           return new ResponseEntity<>(book, HttpStatus.CREATED);
          }  */
    }


}


