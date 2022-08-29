package com.yggdrasil.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yggdrasil.model.RefreshToken;
import com.yggdrasil.model.Users;
import com.yggdrasil.service.RefreshTokenService;
import com.yggdrasil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final String secret;

    @Autowired
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService, UserService userService, @Value("$(jwt.secret)") String secret) {
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
        this.userService = userService;
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
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000 * 12))
                .withClaim("ROLES", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(Algorithm.HMAC256(secret));
        Users users = userService.findByEmail(user.getUsername());

        boolean rememberMe = users.isRememberMe();
        long expireDate;

        if (rememberMe) {
            expireDate = 1461;
        } else {
            expireDate = 4;
        }

        String refresh_token = com.auth0.jwt.JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30L * 60 * 1000 * expireDate))
                .sign(Algorithm.HMAC256(secret));
        response.setHeader("access_token", access_token);
        response.setHeader("refresh_token", refresh_token);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(refresh_token);
        refreshTokenService.addToken(refreshToken);


        Long usersPrevToken = users.getRefreshToken();

        if (usersPrevToken != null) {
            userService.deleteToken(users.getId());
            refreshTokenService.deleteToken(usersPrevToken);
        }

        userService.setToken(users.getId(), refreshToken.getId());

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
