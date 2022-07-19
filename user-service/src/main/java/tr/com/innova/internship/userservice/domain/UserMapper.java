package tr.com.innova.internship.userservice.domain;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tr.com.innova.internship.commonrest.dto.UserDto;
import tr.com.innova.internship.commonrest.rest.mapper.Mapper;

@Component
public class UserMapper implements Mapper<User, UserDto> {
    private final ModelMapper modelMapper;

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
