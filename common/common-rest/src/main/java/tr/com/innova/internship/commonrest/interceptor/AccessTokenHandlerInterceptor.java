package tr.com.innova.internship.commonrest.interceptor;

import com.innova.internship.jwtsupport.TokenVerifier;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import tr.com.innova.internship.commonrest.CheckAccessToken;
import tr.com.innova.internship.commonrest.NotAuthenticatedException;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static tr.com.innova.internship.commonrest.utils.TokenUtils.extractTokenFromHttpHeaders;


public class AccessTokenHandlerInterceptor implements HandlerInterceptor {
    private final TokenVerifier tokenVerifier;

    public AccessTokenHandlerInterceptor(TokenVerifier tokenVerifier) {
        this.tokenVerifier = tokenVerifier;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (DispatcherType.REQUEST != request.getDispatcherType()) return true;
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Optional<CheckAccessToken> checkAccessTokenAnnotation
                = getCheckAccessTokenAnnotation((HandlerMethod) handler);

        if (checkAccessTokenAnnotation.isEmpty()) return true;

        CheckAccessToken checkAccessToken = checkAccessTokenAnnotation.get();

        String token = extractTokenFromHttpHeaders(request)
                .orElseThrow(NotAuthenticatedException::new);

        this.verify(token, checkAccessToken.discardExpiry());


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }


    private void verify(String token, boolean discardExpirity) {
        tokenVerifier.verify(token, discardExpirity);
    }

    private Optional<CheckAccessToken> getCheckAccessTokenAnnotation(HandlerMethod handler) {
        CheckAccessToken checkAccessTokenAnnotation = handler.getMethodAnnotation(CheckAccessToken.class);
        return Optional.ofNullable(checkAccessTokenAnnotation);
    }
}
