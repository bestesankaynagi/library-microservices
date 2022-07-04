package tr.com.innova.internship.bookservice.domain;

import com.innova.internship.loggingsupport.rest.mapper.Mapper;
import dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<Book, BookDto> {
    // todo object mapper ile type convert işleri yapılmalı

    @Override
    public Book toEntity(BookDto dto) {
        return null;
    }

    @Override
    public BookDto toDto(Book entity) {
        return null;
    }
}
