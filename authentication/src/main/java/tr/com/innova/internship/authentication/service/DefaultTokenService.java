package tr.com.innova.internship.authentication.service;

import com.innova.internship.jwtsupport.JwtConstants;
import com.innova.internship.jwtsupport.JwtToken;
import com.innova.internship.jwtsupport.TokenManager;
import com.innova.internship.jwtsupport.TokenParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tr.com.innova.internship.authentication.client.UserServiceClient;
import tr.com.innova.internship.commonrest.dto.LoginDto;
import tr.com.innova.internship.commonrest.dto.UserDto;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;


@Service
public class DefaultTokenService {
    private final TokenManager tokenManager;
    @Value("${access.token.duration.in.minutes}")
    private Long accessTokenDuration;

    @Value("${refresh.token.duration.in.minutes}")
    private Long refreshTokenDuration;

    private final UserServiceClient userServiceClient;

    public DefaultTokenService(TokenManager tokenManager, UserServiceClient userServiceClient) {
        this.tokenManager = tokenManager;
        this.userServiceClient = userServiceClient;
    }

    public JwtToken login(LoginDto loginDto) {
        Mono<UserDto> userValidation = userServiceClient.validateLoginAttempt(loginDto);

        return userValidation.map(this::generateToken).block();

    }

    private JwtToken generateToken(UserDto userDto) {
        String refreshTokenId = UUID.randomUUID().toString();
        TokenParam accessTokenParams = TokenParam.builder()
                .audience(new String[]{JwtConstants.AUDIENCE_ACCESS})
                .put(JwtConstants.USER, userDto.getId())
                .put(JwtConstants.TOKEN_ID, UUID.randomUUID().toString())
                .put(JwtConstants.REFRESH_TOKEN_ID, refreshTokenId)
                .expireAt(Date.from(Instant.now().plus(accessTokenDuration, ChronoUnit.MINUTES)))
                .build();

        TokenParam refreshTokenParams = TokenParam.builder()
                .put(JwtConstants.TOKEN_ID, refreshTokenId)
                .put(JwtConstants.USER, userDto.getId())
                .expireAt(Date.from(Instant.now().plus(refreshTokenDuration, ChronoUnit.MINUTES)))
                .build();


        final String accessToken = tokenManager.generateToken(accessTokenParams);
        final String refreshToken = tokenManager.generateToken(refreshTokenParams);
        return new JwtToken(accessToken, refreshToken);
    }
}
