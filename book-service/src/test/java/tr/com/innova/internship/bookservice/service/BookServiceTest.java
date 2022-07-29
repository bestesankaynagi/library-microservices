package tr.com.innova.internship.bookservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tr.com.innova.internship.bookservice.domain.Book;
import tr.com.innova.internship.bookservice.domain.BookMapper;
import tr.com.innova.internship.bookservice.repository.BookRepository;
import tr.com.innova.internship.commonrest.dto.BookDto;
import tr.com.innova.internship.commonrest.dto.RentDto;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BookServiceTest {
    private BookService bookService;
    private BookRepository bookRepository;
    private BookMapper bookMapper;

    private Book MOCK_BOOK = new Book("1", "bookName", "bookAuthor", 2000, "bookPublisher", null, new Date());
    private RentDto MOCK_RENT_DTO = new RentDto(MOCK_BOOK.getId(), "user_id_1", Date.from(Instant.now().plus(10, ChronoUnit.DAYS)));


    @BeforeEach
    public void setUp() throws Exception {
        bookRepository = Mockito.mock(BookRepository.class);
        bookMapper = new BookMapper();

        bookService = new BookService(bookRepository, bookMapper);
    }



    @Test
    void getAllBooks() {
        List<Book> actualList = List.of(MOCK_BOOK);
        when(bookRepository.findAll()).thenReturn(actualList);

        List<BookDto> bookDtoList = bookService.getAllBooks();
        bookDtoList.forEach(bookDto -> {
            Optional<Book> optionalBook = actualList.stream().filter(book -> book.getId().equals(bookDto.getId())).findFirst();
            assertEquals(true, optionalBook.isPresent());

            Book actual = optionalBook.get();

            assertEquals(bookDto.getId(), actual.getId());
            assertEquals(bookDto.getTakenBy(), actual.getTakenBy());
            assertEquals(bookDto.getAuthor(), actual.getAuthor());
            assertEquals(bookDto.getDueDate(), actual.getDueDate());
            assertEquals(bookDto.getName(), actual.getName());
            assertEquals(bookDto.getPublisher(), actual.getPublisher());
            assertEquals(bookDto.getYear(), actual.getYear());
        });
    }

    @Test
    void saveBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(MOCK_BOOK);

        BookDto returnedBookDto = bookService.saveBook(bookMapper.toDto(MOCK_BOOK));

        assertEquals(MOCK_BOOK.getId(), returnedBookDto.getId());
        assertEquals(MOCK_BOOK.getTakenBy(), returnedBookDto.getTakenBy());
        assertEquals(MOCK_BOOK.getAuthor(), returnedBookDto.getAuthor());
        assertEquals(MOCK_BOOK.getDueDate(), returnedBookDto.getDueDate());
        assertEquals(MOCK_BOOK.getName(), returnedBookDto.getName());
        assertEquals(MOCK_BOOK.getPublisher(), returnedBookDto.getPublisher());
        assertEquals(MOCK_BOOK.getYear(), returnedBookDto.getYear());
    }

    @Test
    void deleteById() {
        bookService.deleteById("1");
        verify(bookRepository).deleteById(Mockito.any(String.class));
    }

    @Test
    void findById() {
        String queryId = MOCK_BOOK.getId();
        when(bookRepository.findById(queryId)).thenReturn(Optional.of(MOCK_BOOK));

        BookDto returnedBookDto = bookService.findById(queryId);

        assertEquals(MOCK_BOOK.getId(), returnedBookDto.getId());
        assertEquals(MOCK_BOOK.getTakenBy(), returnedBookDto.getTakenBy());
        assertEquals(MOCK_BOOK.getAuthor(), returnedBookDto.getAuthor());
        assertEquals(MOCK_BOOK.getDueDate(), returnedBookDto.getDueDate());
        assertEquals(MOCK_BOOK.getName(), returnedBookDto.getName());
        assertEquals(MOCK_BOOK.getPublisher(), returnedBookDto.getPublisher());
        assertEquals(MOCK_BOOK.getYear(), returnedBookDto.getYear());

    }

    @Test
    void findByIdShouldThrowException() {
        String queryId = MOCK_BOOK.getId();
        when(bookRepository.findById(queryId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.findById(queryId));
    }

    @Test
    void rentBook() {
        when(bookRepository.findById(MOCK_RENT_DTO.getBookId())).thenReturn(Optional.of(MOCK_BOOK));
        Book expectedBook = new Book(MOCK_BOOK.getId(),
                MOCK_BOOK.getName(),
                MOCK_BOOK.getAuthor(),
                MOCK_BOOK.getYear(),
                MOCK_BOOK.getPublisher(),
                MOCK_RENT_DTO.getTakenBy(),
                MOCK_RENT_DTO.getDueDate());
        when(bookRepository.save(any(Book.class))).thenReturn(expectedBook);

        BookDto actualBookDto = bookService.rentBook(MOCK_RENT_DTO);
        assertEquals(expectedBook.getId(), actualBookDto.getId());
        assertEquals(expectedBook.getTakenBy(), actualBookDto.getTakenBy());
        assertEquals(expectedBook.getDueDate(), actualBookDto.getDueDate());

    }

    @Test
    void rentBookAlreadyTakenShouldThrowException(){
        Book expectedBook = new Book(MOCK_BOOK.getId(),
                MOCK_BOOK.getName(),
                MOCK_BOOK.getAuthor(),
                MOCK_BOOK.getYear(),
                MOCK_BOOK.getPublisher(),
                MOCK_RENT_DTO.getTakenBy(),
                MOCK_RENT_DTO.getDueDate());

        when(bookRepository.findById(MOCK_RENT_DTO.getBookId())).thenReturn(Optional.of(expectedBook));

        assertThrows(RuntimeException.class, () -> bookService.rentBook(MOCK_RENT_DTO));
    }

    @Test
    void rentBookShouldThrowException(){
        when(bookRepository.findById(MOCK_RENT_DTO.getBookId())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> bookService.rentBook(MOCK_RENT_DTO));
    }
}