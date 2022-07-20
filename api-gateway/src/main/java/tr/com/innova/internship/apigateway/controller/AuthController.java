package tr.com.innova.internship.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tr.com.innova.internship.apigateway.client.AuthenticationServiceApiClient;
import tr.com.innova.internship.commonrest.dto.LoginDto;

@RestController
public class AuthController {
    private final AuthenticationServiceApiClient authenticationServiceApiClient;

    public AuthController(AuthenticationServiceApiClient authenticationServiceApiClient) {
        this.authenticationServiceApiClient = authenticationServiceApiClient;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(
                authenticationServiceApiClient.login(loginDto)
                        .block()
        );
    }
}
