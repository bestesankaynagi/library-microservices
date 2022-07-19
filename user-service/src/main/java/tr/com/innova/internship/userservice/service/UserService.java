package tr.com.innova.internship.userservice.service;

import org.springframework.stereotype.Service;
import tr.com.innova.internship.commonrest.dto.LoginDto;
import tr.com.innova.internship.commonrest.dto.UserDto;
import tr.com.innova.internship.userservice.domain.UserMapper;
import tr.com.innova.internship.userservice.helper.Sha256HashManager;
import tr.com.innova.internship.userservice.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Sha256HashManager sha256HashManager;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.sha256HashManager = new Sha256HashManager();
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto saveUser(UserDto userDto) {
        userDto.setPassword(sha256HashManager.hash(userDto.getPassword()));
        return userMapper.toDto(userRepository.save(this.userMapper.toEntity(userDto)));
    }

    public void deleteById(String userID) {
        userRepository.deleteById(userID);
    }

    public UserDto findById(String userID) {
        return userRepository.findById(userID)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("No record found for given id: " + userID));
    }

    public UserDto validateLoginAttempt(LoginDto loginDto) {
        return userRepository.findByEmailAndPassword(loginDto.getEmail(), sha256HashManager.hash(loginDto.getPassword()))
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Not a valid login attempt!"));
    }
}
