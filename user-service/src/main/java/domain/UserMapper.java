package domain;

import com.innova.internship.loggingsupport.rest.mapper.Mapper;
import dto.BookDto;
import dto.UserDto;
import org.modelmapper.ModelMapper;

public class UserMapper implements Mapper<User, UserDto> {
    private ModelMapper modelMapper;

    public UserMapper() {
        this.modelMapper = new ModelMapper();
    }
    @Override
    public UserDto toDto (User entity){
        return modelMapper.map(entity, UserDto.class);

    }
    @Override
    public User toEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }
}