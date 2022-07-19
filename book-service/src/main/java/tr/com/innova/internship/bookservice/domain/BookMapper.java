package tr.com.innova.internship.bookservice.domain;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tr.com.innova.internship.commonrest.dto.BookDto;
import tr.com.innova.internship.commonrest.rest.mapper.Mapper;

@Component
public class BookMapper implements Mapper<Book, BookDto> {

    private final ModelMapper modelMapper;

    public BookMapper() {
        this.modelMapper = new ModelMapper();
    }

    @Override

    public BookDto toDto(Book entity) {
        return modelMapper.map(entity, BookDto.class);
    }

    @Override
    public Book toEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }
}
