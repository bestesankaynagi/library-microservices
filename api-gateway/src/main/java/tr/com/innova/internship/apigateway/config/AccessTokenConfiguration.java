package tr.com.innova.internship.apigateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tr.com.innova.internship.commonrest.config.AccessTokenInterceptorConfig;
import tr.com.innova.internship.commonrest.config.WebConfiguration;


@Configuration
@Import({AccessTokenInterceptorConfig.class, WebConfiguration.class})
public class AccessTokenConfiguration {
}
