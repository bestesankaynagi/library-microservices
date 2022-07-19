package tr.com.innova.internship.commonrest;

import org.springframework.web.reactive.function.client.WebClient;

public class AbstractRestClient {
    public WebClient createWebClient(String url) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
