package tr.com.innova.internship.apigateway.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import tr.com.innova.internship.commonrest.AbstractRestClient;
import tr.com.innova.internship.commonrest.dto.BookDto;
import tr.com.innova.internship.commonrest.dto.RentDto;

@Component
public class BookServiceApiClient extends AbstractRestClient {
    @Value("${book-service.url}")
    private String bookServiceUrl;

    public Mono<Object> getBookList() {
        return createWebClient(String.format("%s/books", bookServiceUrl))
                .get()
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> getBook(String id) {
        return createWebClient(String.format("%s/books/%s", bookServiceUrl, id))
                .get()
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> saveBook(BookDto bookDto) {
        return createWebClient(String.format("%s/books", bookServiceUrl))
                .post()
                .body(BodyInserters.fromValue(bookDto))
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> deleteBook(String id) {
        return createWebClient(String.format("%s/books/%s", bookServiceUrl, id))
                .delete()
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<BookDto> rentBook(RentDto rentDto) {
        return createWebClient(String.format("%s/books/rent", bookServiceUrl))
                .post()
                .body(BodyInserters.fromValue(rentDto))
                .retrieve()
                .bodyToMono(BookDto.class);
    }

}
