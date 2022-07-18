package tr.com.innova.internship.bookservice.domain;

import com.innova.internship.loggingsupport.rest.mapper.Mapper;
import dto.BookDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<Book, BookDto> {
    // todo object mapper ile type convert işleri yapılmalı

    private ModelMapper modelMapper;

    public BookMapper() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    //convert Entity into Dto
    public BookDto toDto(Book entity) {

//        BookDto bookDto = BookDto.builder()
//                .author(entity.getAuthor())
//                .name(entity.getName())
//                .build();

//        BookDto bookDto = new BookDto();
//        bookDto.setAuthor(entity.getAuthor());
//        bookDto.setName(entity.getName());
//        bookDto.setDueDate(entity.getDueDate());
//        bookDto.setPublisher(entity.getPublisher());
//        bookDto.setYear(entity.getYear());
//        bookDto.setTakenBy(entity.getTakenBy());
//
//        bookDto.setID(entity.getID());

        return modelMapper.map(entity, BookDto.class);
    }

    @Override
    //convert Dto to entity
    public Book toEntity(BookDto bookDto) {


//        Book book = new Book();
//
//        book.setID(bookDto.getID());
//
//        book.setAuthor(bookDto.getAuthor());
//        book.setDueDate(bookDto.getDueDate());
//        book.setName(bookDto.getName());
//        book.setPublisher(bookDto.getPublisher());
//        book.setYear(bookDto.getYear());
//        book.setTakenBy(bookDto.getTakenBy());

        //Book entity=new Book();
        //entity.setAuthor(bookDto.getAuthor());

        return modelMapper.map(bookDto, Book.class);
    }

}
