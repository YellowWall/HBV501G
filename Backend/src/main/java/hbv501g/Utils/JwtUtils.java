package hbv501g.Utils;

import java.util.Date;
import java.util.List;
import hbv501g.Persistence.Entities.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

@Component
public class JwtUtils {

    //@Value("${jwt.secret}")
    private String secret = "asdfasdf";
    private long tokenValidityPeriodInMilliseconds = 60 * 60 * 1000; // one hour

    private JwtParser jwtParser;

    public JwtUtils() {
        this.jwtParser = Jwts.parser();
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("name", user.getName());
        Date validity = new Date((new Date()).getTime() + tokenValidityPeriodInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJwt(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            System.out.println("ex: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            System.out.println("ex: " + ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        String prefix = "Bearer ";
        if (bearerToken != null && bearerToken.startsWith(prefix)) {
            return bearerToken.substring(prefix.length());
        }

        return null;
    }

    public boolean validateToken(HttpServletRequest request) {
        String token = resolveToken(request);
        try {
            if (!Jwts.parser().setSigningKey(secret).isSigned(token))
                return false;
        } catch (JwtException e) {
            //don't trust the JWT!
            return false;
        }

        return true;
    }

    public boolean validateClaims(Claims claims) {
        return claims.getExpiration().after(new Date());
    }

    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    private List<String> getRoles(Claims claims) {
        return (List<String>) claims.get("roles");
    }
}