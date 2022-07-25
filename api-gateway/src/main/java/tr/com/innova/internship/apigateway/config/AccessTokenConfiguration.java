package tr.com.innova.internship.apigateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({AccessTokenInterceptorConfig.class, WebConfiguration.class})
public class AccessTokenConfiguration {
}
