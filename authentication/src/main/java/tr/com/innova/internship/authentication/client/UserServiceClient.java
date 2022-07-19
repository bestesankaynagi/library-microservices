package tr.com.innova.internship.authentication.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import tr.com.innova.internship.commonrest.AbstractRestClient;
import tr.com.innova.internship.commonrest.dto.LoginDto;
import tr.com.innova.internship.commonrest.dto.UserDto;

@Service
public class UserServiceClient extends AbstractRestClient {
    @Value("${user-service.url}")
    private String userServiceUrl;

    public Mono<UserDto> validateLoginAttempt(LoginDto loginDto) {
        return createWebClient(String.format("%s/users/validate", userServiceUrl))
                .post()
                .body(BodyInserters.fromValue(loginDto))
                .retrieve()
                .bodyToMono(UserDto.class);
    }
}
