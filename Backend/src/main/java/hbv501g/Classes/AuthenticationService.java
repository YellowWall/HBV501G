package hbv501g.Classes;

import hbv501g.Utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

public class AuthenticationService {

    private static final String AUTH_TOKEN_HEADER_NAME = "Authorization";
    private static final String AUTH_TOKEN = "Baeldung";
    private static final JwtUtils jwtUtils = new JwtUtils();

    public static Authentication getAuthentication(HttpServletRequest request) {
        //String token = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        String token = jwtUtils.resolveToken(request);
        boolean isTokenValid = jwtUtils.validateToken(request);
        System.out.println("TOKEN: " + token + "\n VALID? : " + isTokenValid);

        if (!isTokenValid) {
            throw new BadCredentialsException("Invalid Token");
        }

        return new JWTAuthentication(token, AuthorityUtils.NO_AUTHORITIES);
    }
}
