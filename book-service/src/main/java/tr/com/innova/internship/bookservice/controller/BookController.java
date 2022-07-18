package tr.com.innova.internship.bookservice.controller;

import dto.BookDto;
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
    private List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping
    private Book findBook(@PathVariable int id){
        return bookService.findById(id);
    }


    @PostMapping
    public BookDto saveBook(@RequestBody BookDto bookDto) {
        return bookService.saveBook(bookDto);
        /*public ResponseEntity<Book> createBook(@RequestBody Book book) {
            bookRepository.save(book);
           return new ResponseEntity<>(book, HttpStatus.CREATED);
          }  */
    }

    @DeleteMapping
    public void deleteBook(@PathVariable int id) {
        bookService.deleteById(id);
    }

//    @PutMapping
//    public ResponseEntity<BookDto> updateBook(
//            @Valid @RequestBody BookDto book) {
//        BookDto newBookDto = bookService.updateBook(book);
//
//
//        return ResponseEntity.ok(newBookDto);
//    }

}


