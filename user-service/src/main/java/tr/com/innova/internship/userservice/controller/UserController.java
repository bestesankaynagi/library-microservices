package tr.com.innova.internship.userservice.controller;

import tr.com.innova.internship.userservice.domain.User;
import com.innova.internship.loggingsupport.rest.dto.UserDto;
import org.springframework.web.bind.annotation.*;
import tr.com.innova.internship.userservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    private User findUser(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {

        userService.deleteById(id);
    }
}
