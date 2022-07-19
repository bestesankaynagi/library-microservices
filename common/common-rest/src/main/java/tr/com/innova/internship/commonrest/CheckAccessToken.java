package tr.com.innova.internship.commonrest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CheckAccessToken {
    boolean discardExpiry() default false;

    String tokenParameterName() default "access_token";

    boolean checkHttpParameter() default false;

    boolean checkCookie() default false;
}
