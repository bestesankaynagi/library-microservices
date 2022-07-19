package tr.com.innova.internship.authentication.controller;

import com.innova.internship.jwtsupport.JwtToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tr.com.innova.internship.authentication.service.DefaultTokenService;
import tr.com.innova.internship.commonrest.dto.LoginDto;

@RestController
public class AuthController {

    private final DefaultTokenService tokenService;

    public AuthController(DefaultTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public JwtToken auth(@RequestBody LoginDto loginDto) {
        return tokenService.login(loginDto);
    }
}
