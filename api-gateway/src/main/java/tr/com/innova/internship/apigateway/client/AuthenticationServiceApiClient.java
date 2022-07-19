package tr.com.innova.internship.apigateway.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import tr.com.innova.internship.commonrest.AbstractRestClient;
import tr.com.innova.internship.commonrest.dto.LoginDto;

@Component
public class AuthenticationServiceApiClient extends AbstractRestClient {
    @Value("${authentication-service.url}")
    private String authenticationServiceUrl;

    public Mono<Object> login(LoginDto loginDto) {
        return createWebClient(String.format("%s/login", authenticationServiceUrl))
                .post()
                .body(BodyInserters.fromValue(loginDto))
                .retrieve()
                .bodyToMono(Object.class);

    }
}
