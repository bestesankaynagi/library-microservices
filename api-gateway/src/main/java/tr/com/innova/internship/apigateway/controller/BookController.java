package tr.com.innova.internship.apigateway.controller;

import tr.com.innova.internship.commonrest.dto.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.innova.internship.apigateway.client.BookServiceApiClient;
import tr.com.innova.internship.commonrest.dto.RentDto;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookServiceApiClient bookServiceApiClient;

    BookController(BookServiceApiClient bookServiceApiClient) {
        this.bookServiceApiClient = bookServiceApiClient;
    }


    @GetMapping
    public ResponseEntity<Object> getBookList() {
        return ResponseEntity.ok(
                bookServiceApiClient.getBookList()
                        .block()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBook(@PathVariable String id) {
        return ResponseEntity.ok(
                bookServiceApiClient.getBook(id)
                        .block()
        );
    }

    @PostMapping
    public ResponseEntity<Object> saveBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(
                bookServiceApiClient.saveBook(bookDto)
                        .block()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable String id) {
        return ResponseEntity.ok(
                bookServiceApiClient.deleteBook(id)
                        .block()
        );
    }

    @PostMapping("/rent")
    public ResponseEntity<BookDto> rentBook(@RequestBody RentDto rentDto) {
        return ResponseEntity.ok(
                bookServiceApiClient.rentBook(rentDto)
                        .block()
        );
    }

}

