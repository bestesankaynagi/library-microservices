package tr.com.innova.internship.commonrest.config;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tr.com.innova.internship.commonrest.interceptor.AccessTokenHandlerInterceptor;

public class WebConfiguration implements WebMvcConfigurer {

	private final AccessTokenHandlerInterceptor accessTokenHandlerInterceptor;

	public WebConfiguration(AccessTokenHandlerInterceptor accessTokenHandlerInterceptor) {
		this.accessTokenHandlerInterceptor = accessTokenHandlerInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(accessTokenHandlerInterceptor).order(Ordered.HIGHEST_PRECEDENCE);
	}
}
