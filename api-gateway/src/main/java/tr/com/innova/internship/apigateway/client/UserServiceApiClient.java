package tr.com.innova.internship.apigateway.client;

import com.innova.internship.loggingsupport.rest.AbstractRestClient;
import com.innova.internship.loggingsupport.rest.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;


@Component
public class UserServiceApiClient extends AbstractRestClient {
    @Value("${user-service.url}")
    private String userServiceUrl;

    public Mono<Object> getAllUsers() {
        return createWebClient(String.format("%s/users", userServiceUrl))
                .get()
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> getUser(String id) {
        return createWebClient(String.format("%s/users/%s", userServiceUrl, id))
                .get()
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> saveUser(UserDto userDto) {
        return createWebClient(String.format("%s/users", userServiceUrl))
                .post()
                .body(BodyInserters.fromValue(userDto))
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> deleteUser(String id) {
        return createWebClient(String.format("%s/users/%s", userServiceUrl, id))
                .delete()
                .retrieve()
                .bodyToMono(Object.class);
    }
}
