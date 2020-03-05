package tm.arzuv.app.security;

public class JwtProperties {
    public static final String SECRET = "helloworld123";
    public static final int EXPIRATION_TIME = 84000000; //10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}