/*
package hbv501g.Classes;

import hbv501g.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    public JwtAuthFilter() {}

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtils.resolveToken(request);

        System.out.println("jwttoken : " + token);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("token : " + token);
        Claims claims = jwtUtils.resolveClaims(request);
        System.out.println("claims: " + claims.toString());

        if (claims != null && jwtUtils.validateClaims(claims)){
            String username = claims.getSubject();
            System.out.println("username : " + username);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(username,"",new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
*/