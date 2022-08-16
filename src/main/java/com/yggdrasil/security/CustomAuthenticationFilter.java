package com.yggdrasil.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yggdrasil.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.security.config.Elements.JWT;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final String secret;

    @Autowired
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, @Value("$(jwt.secret)") String secret) {
        this.authenticationManager = authenticationManager;
        this.secret = secret;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Users creds = new ObjectMapper()
                    .readValue(request.getInputStream(), Users.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String access_token = com.auth0.jwt.JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(Algorithm.HMAC256(secret));
        String refresh_token = com.auth0.jwt.JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .sign(Algorithm.HMAC256(secret));

        Cookie jwtAccessTokenCookie = new Cookie("access_token", access_token);
        jwtAccessTokenCookie.setMaxAge(86400);
        jwtAccessTokenCookie.setSecure(true);
        jwtAccessTokenCookie.setHttpOnly(true);
        jwtAccessTokenCookie.setPath("/");

        System.out.println(jwtAccessTokenCookie.getValue());

        Cookie jwtRefreshTokenCookie = new Cookie("refresh_token", refresh_token);

        jwtRefreshTokenCookie.setMaxAge(86400);
        jwtRefreshTokenCookie.setSecure(true);
        jwtRefreshTokenCookie.setHttpOnly(true);
        jwtRefreshTokenCookie.setPath("/");

        response.addCookie(jwtAccessTokenCookie);
        response.addCookie(jwtRefreshTokenCookie);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);


        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
