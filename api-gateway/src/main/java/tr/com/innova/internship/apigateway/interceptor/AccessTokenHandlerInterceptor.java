package tr.com.innova.internship.apigateway.interceptor;

import com.innova.internship.jwtsupport.JwtConstants;
import com.innova.internship.jwtsupport.JwtUtils;
import com.innova.internship.jwtsupport.TokenVerifier;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import tr.com.innova.internship.apigateway.service.TokenRevocationService;
import tr.com.innova.internship.commonrest.CheckAccessToken;
import tr.com.innova.internship.commonrest.NotAuthenticatedException;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static tr.com.innova.internship.commonrest.utils.TokenUtils.extractTokenFromHttpHeaders;


public class AccessTokenHandlerInterceptor implements HandlerInterceptor {
    private final TokenVerifier tokenVerifier;
    private final TokenRevocationService tokenRevocationService;

    public AccessTokenHandlerInterceptor(TokenVerifier tokenVerifier, TokenRevocationService tokenRevocationService) {
        this.tokenVerifier = tokenVerifier;
        this.tokenRevocationService = tokenRevocationService;
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

        String refreshTokenId = JwtUtils.decode(token).getClaim(JwtConstants.REFRESH_TOKEN_ID).asString();
        if(tokenRevocationService.isRevoked(refreshTokenId)) throw new TokenRevokedException();

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
