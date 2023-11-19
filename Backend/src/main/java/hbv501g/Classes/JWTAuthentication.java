package hbv501g.Classes;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthentication extends AbstractAuthenticationToken {
    private final String jwtToken;

    public JWTAuthentication(String jwtToken, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.jwtToken = jwtToken;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return jwtToken;
    }
}
