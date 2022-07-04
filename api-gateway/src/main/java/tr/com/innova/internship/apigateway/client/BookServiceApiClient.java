package tr.com.innova.internship.apigateway.client;

import com.innova.internship.loggingsupport.rest.AbstractRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class BookServiceApiClient extends AbstractRestClient {
    @Value("${book-service.url}")
    private String bookServiceUrl;

    public Mono<Object> getBookList() {
        return createWebClient(String.format("%s/book", bookServiceUrl))
                .get()
                .retrieve()
                .bodyToMono(Object.class);
    }
}
