package tr.com.innova.internship.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.innova.internship.apigateway.client.BookServiceApiClient;

@RestController
@RequestMapping("/book")
public class LibraryController{

    private final BookServiceApiClient bookServiceApiClient;

    LibraryController(BookServiceApiClient bookServiceApiClient) {
        this.bookServiceApiClient = bookServiceApiClient;
    }


    @GetMapping
    public ResponseEntity<Object> getBookList(){
        return ResponseEntity.ok(
                bookServiceApiClient.getBookList()
                        .block()
        );
    }




}






/*import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.BookService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/library")
@AllArgsConstructor
public class LibraryController {
    private final BookService bookService;


    @GetMapping
    public ResponseEntity<List<Library>> getbooks() {
        return new ResponseEntity<>(bookService.getbooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> getLibrary(@PathVariable String id) {
        return new ResponseEntity<>(getLibraryById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Library> createLibrary(@RequestBody org.apache.tomcat.jni.Library newLibrary) {

        return new ResponseEntity(bookService.createLibrary(newLibrary), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> getLibrary(@PathVariable String id, @RequestBody Library newLibrary) {
        Library oldlibrary = getLibraryById(id);
        oldlibrary.setName(newLibrary.getName());
        oldlibrary.setCreateDate(new Date());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Library getLibraryById(String id) {

        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable String id) {
        bookService.deleteLibrary(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    private Library getLibraryById(String id) {
//        return bookService.getLibraryById(id);
//    }

}*/
