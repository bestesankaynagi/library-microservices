package service;

import domain.User;
import domain.UserMapper;
import dto.UserDto;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
    public UserDto saveUser(UserDto userDto) {

        return userMapper.toDto(userRepository.save(this.userMapper.toEntity(userDto)));
    }
    public UserDto updateUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(userDto.getID());
        if (optionalUser.isPresent()) {

            userDto.setName("..");
            return userMapper.toDto(userRepository.save(this.userMapper.toEntity(userDto)));
        } else {
            throw new RuntimeException("No field found.");
        }
    }

    public void deleteById(int userID) {
        userRepository.deleteById(userID);

    }

    public User findById(int userID){
        Optional<User> userResponse =  userRepository.findById(userID);
        return userResponse.orElseThrow(() -> new RuntimeException("No record found for given id: "+userID));
    }
}
