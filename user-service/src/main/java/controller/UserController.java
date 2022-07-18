package controller;
import domain.User;
import dto.UserDto;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;

@RestController
@Repository
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping
    private List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping
    private User findUser(@PathVariable int id){
        return userService.findById(id);
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }
    @DeleteMapping
    public void deleteUser(@PathVariable int id) {

        userService.deleteById(id);
    }
}
