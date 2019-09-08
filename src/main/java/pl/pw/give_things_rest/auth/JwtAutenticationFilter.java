package pl.pw.give_things_rest.auth;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Authorization;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JwtAutenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAutenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> grantedAuthorities = authResult.getAuthorities();
        claims.put("authorities", grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")));

        Date date = new Date(System.currentTimeMillis() + (40 * 60 * 1000));
        String token = Jwts
                .builder()
                .setClaims(claims)
                .setSubject(((UserDetails) authResult.getPrincipal()).getUsername())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, "passwd")
                .compact();

        response.addHeader("Authorization","Bearer "+token);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Map<String,String> responseBody=new HashMap<>();
        responseBody.put("token",token);
        responseBody.put("date",date.toString());
        new ObjectMapper().writeValue(response.getWriter(),responseBody);

    }
}
