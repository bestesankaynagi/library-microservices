package tr.com.innova.internship.bookservice.controller;

import com.innova.internship.loggingsupport.rest.dto.BookDto;
import org.springframework.web.bind.annotation.*;
import tr.com.innova.internship.bookservice.domain.Book;
import tr.com.innova.internship.bookservice.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    private List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    private Book findBook(@PathVariable String id) {
        return bookService.findById(id);
    }


    @PostMapping
    public BookDto saveBook(@RequestBody BookDto bookDto) {
        return bookService.saveBook(bookDto);

    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteById(id);
    }

}


