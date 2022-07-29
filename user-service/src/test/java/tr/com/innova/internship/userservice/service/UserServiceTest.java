package tr.com.innova.internship.userservice.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tr.com.innova.internship.commonrest.dto.LoginDto;
import tr.com.innova.internship.commonrest.dto.UserDto;
import tr.com.innova.internship.userservice.domain.User;
import tr.com.innova.internship.userservice.domain.UserMapper;
import tr.com.innova.internship.userservice.helper.Sha256HashManager;
import tr.com.innova.internship.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private Sha256HashManager sha256HashManager;
    private UserService userService;

    private User MOCK_USER = new User("1", "name", "surname", "email", 1998, "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");


    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userMapper = new UserMapper();
        sha256HashManager = new Sha256HashManager();
        userService = new UserService(userRepository, userMapper);
    }

    @Test
    void getAllUsers() {

        List<User> actualList = List.of(MOCK_USER);
        when(userRepository.findAll()).thenReturn(actualList);

        List<UserDto> userDtoList = userService.getAllUsers();
        userDtoList.forEach(userDto -> {
            Optional<User> optionalUser = actualList.stream().filter(user -> user.getId().equals(userDto.getId())).findFirst();
            assertEquals(true, optionalUser.isPresent());

            User actual = optionalUser.get();

            assertEquals(userDto.getId(), actual.getId());
            assertEquals(userDto.getName(), actual.getName());
            assertEquals(userDto.getYear(), actual.getYear());
            assertEquals(userDto.getPassword(), actual.getPassword());
            assertEquals(userDto.getEmail(), actual.getEmail());
            assertEquals(userDto.getSurname(), actual.getSurname());
        });
    }

    @Test
    void saveUser() {

        when(userRepository.save(any(User.class))).thenReturn(MOCK_USER);

        UserDto returnedUserDto = userService.saveUser(userMapper.toDto(MOCK_USER));

        assertEquals(MOCK_USER.getId(), returnedUserDto.getId());
        assertEquals(MOCK_USER.getName(), returnedUserDto.getName());
        assertEquals(MOCK_USER.getYear(), returnedUserDto.getYear());
        assertEquals(MOCK_USER.getPassword(), returnedUserDto.getPassword());
        assertEquals(MOCK_USER.getEmail(), returnedUserDto.getEmail());
        assertEquals(MOCK_USER.getSurname(), returnedUserDto.getSurname());
    }

    @Test
    void deleteById() {
        userService.deleteById("1");
        verify(userRepository).deleteById(Mockito.any(String.class));
    }

    @Test
    void findById() {
        String queryId = MOCK_USER.getId();
        when(userRepository.findById(queryId)).thenReturn(Optional.of(MOCK_USER));

        UserDto returnedUserDto = userService.findById(queryId);

        assertEquals(MOCK_USER.getId(), returnedUserDto.getId());
        assertEquals(MOCK_USER.getName(), returnedUserDto.getName());
        assertEquals(MOCK_USER.getYear(), returnedUserDto.getYear());
        assertEquals(MOCK_USER.getPassword(), returnedUserDto.getPassword());
        assertEquals(MOCK_USER.getEmail(), returnedUserDto.getEmail());
        assertEquals(MOCK_USER.getSurname(), returnedUserDto.getSurname());
    }

    @Test
    void validateLoginAttempt() {
        when(userRepository.findByEmailAndPassword(any(String.class), any(String.class))).thenReturn(Optional.of(MOCK_USER));

        LoginDto loginDto = new LoginDto(MOCK_USER.getEmail(), "123456");

        UserDto returnedUserDto = userService.validateLoginAttempt(loginDto);

        assertEquals(MOCK_USER.getId(), returnedUserDto.getId());
        assertEquals(MOCK_USER.getName(), returnedUserDto.getName());
        assertEquals(MOCK_USER.getYear(), returnedUserDto.getYear());
        assertEquals(MOCK_USER.getPassword(), returnedUserDto.getPassword());
        assertEquals(MOCK_USER.getEmail(), returnedUserDto.getEmail());
        assertEquals(MOCK_USER.getSurname(), returnedUserDto.getSurname());
    }

    @Test
    void validateLoginAttemptShouldThrowException() {
        LoginDto loginDto = new LoginDto(MOCK_USER.getEmail(), "123456");
        when(userRepository.findByEmailAndPassword(any(String.class), any(String.class))).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.validateLoginAttempt(loginDto));
    }


    @Test
    void findByIdShouldThrowException() {
        String queryId = MOCK_USER.getId();
        when(userRepository.findById(queryId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.findById(queryId));
    }

    @Test
    public void testSHA256() {

        assertEquals(
                "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92",
                sha256HashManager.hash("123456")
        );
    }
}