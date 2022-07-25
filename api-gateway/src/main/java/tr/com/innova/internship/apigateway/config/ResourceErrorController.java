package tr.com.innova.internship.apigateway.config;

import com.auth0.jwt.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tr.com.innova.internship.apigateway.interceptor.TokenRevokedException;
import tr.com.innova.internship.commonrest.NotAuthenticatedException;


@ControllerAdvice
@RestController
public class ResourceErrorController {
	@ExceptionHandler({
			JWTDecodeException.class,
			JWTVerificationException.class,
			NotAuthenticatedException.class,
			AlgorithmMismatchException.class,
			SignatureVerificationException.class,
			TokenExpiredException.class,
			InvalidClaimException.class,
			TokenRevokedException.class
	})
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	ResponseEntity<String> onNotAuthenticatedException(Exception ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ex.getMessage());
	}
}
