package tr.com.innova.internship.apigateway.interceptor;


public class TokenRevokedException extends RuntimeException{
    public TokenRevokedException() {
        super("Token is already revoked.");
    }
}
