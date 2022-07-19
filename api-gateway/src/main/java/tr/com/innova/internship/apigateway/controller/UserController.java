package tr.com.innova.internship.apigateway.controller;

import com.innova.internship.loggingsupport.rest.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.innova.internship.apigateway.client.UserServiceApiClient;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceApiClient userServiceApiClient;

    public UserController(UserServiceApiClient userServiceApiClient) {
        this.userServiceApiClient = userServiceApiClient;
    }

    @GetMapping
    public ResponseEntity<Object> getUserList() {
        return ResponseEntity.ok(
                userServiceApiClient.getAllUsers()
                        .block()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable String id) {
        return ResponseEntity.ok(
                userServiceApiClient.getUser(id)
                        .block()
        );
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(
                userServiceApiClient.saveUser(userDto)
                        .block()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok(
                userServiceApiClient.deleteUser(id)
                        .block()
        );
    }

}
