package tr.com.innova.internship.apigateway.controller;

import com.innova.internship.jwtsupport.JwtConstants;
import com.innova.internship.jwtsupport.JwtUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import tr.com.innova.internship.apigateway.client.AuthenticationServiceApiClient;
import tr.com.innova.internship.apigateway.service.TokenRevocationService;
import tr.com.innova.internship.commonrest.CheckAccessToken;
import tr.com.innova.internship.commonrest.dto.LoginDto;

@RestController
public class AuthController {
    private final AuthenticationServiceApiClient authenticationServiceApiClient;
    private final TokenRevocationService tokenRevocationService;

    public AuthController(AuthenticationServiceApiClient authenticationServiceApiClient, TokenRevocationService tokenRevocationService) {
        this.authenticationServiceApiClient = authenticationServiceApiClient;
        this.tokenRevocationService = tokenRevocationService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(
                authenticationServiceApiClient.login(loginDto)
                        .block()
        );
    }

    @PostMapping("/logout")
    @CheckAccessToken
    public ResponseEntity<Void> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String refreshTokenId =  JwtUtils.decode(token).getClaim(JwtConstants.REFRESH_TOKEN_ID).asString();

        tokenRevocationService.revokeToken(refreshTokenId);
        return ResponseEntity.ok().build();
    }
}
