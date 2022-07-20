package tr.com.innova.internship.commonrest.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class TokenUtils {
	public static final String BEARER_TYPE = "Bearer ";
	public static final String ACCESS_TOKEN_COOKIE_NAME = "access_token";

	private TokenUtils() {
	}

	public static Optional<String> extractTokenFromHttpHeaders(HttpServletRequest request) {
		String authHeaderValue = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (StringUtils.isEmpty(authHeaderValue))
			return Optional.empty(); // return empty if no header found with name "Authorization"

		if (!authHeaderValue.toLowerCase().startsWith(BEARER_TYPE.toLowerCase())) {
			return Optional.empty();
		}

		return Optional.of(authHeaderValue.substring(BEARER_TYPE.length()).trim());
	}

	public static Optional<String> extractTokenFromHttpParameter(HttpServletRequest request, String name) {
		return Optional.ofNullable(request.getParameter(name));
	}

	public static Optional<String> extractTokenFromCookie(HttpServletRequest request) {
		return Optional.ofNullable(WebUtils.getCookie(request, ACCESS_TOKEN_COOKIE_NAME)).map(Cookie::getValue);
	}
}
