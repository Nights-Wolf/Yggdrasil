package com.yggdrasil.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yggdrasil.model.Users;
import com.yggdrasil.service.RefreshTokenService;
import com.yggdrasil.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationRestController {

    private final UserService userService;
    private final String secret;

    public AuthenticationRestController(UserService userService, RefreshTokenService refreshTokenService, @Value("$(jwt.secret)") String secret) {
        this.userService = userService;
        this.secret = secret;
    }

    @GetMapping("/refresh/token")
    private void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_Token = authorizationHeader.substring("Bearer ".length());
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_Token);
                String email = decodedJWT.getSubject();
                Users users = userService.findByEmail(email);
                String access_token = com.auth0.jwt.JWT.create()
                        .withSubject(users.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000 * 120))
                        .withClaim("ROLES", users.getGrantedAuthorities())
                        .sign(Algorithm.HMAC256(secret));
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                response.setHeader("access_token", access_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @PostMapping("/remember/{email}")
    private void rememberMeCheck(@PathVariable("email") String email) throws IOException {
        userService.rememberMeCheck(email);
    }

    @PostMapping("/signOut")
    private ResponseEntity<Void> signOut(HttpServletRequest request) {
        SecurityContextHolder.clearContext();

        Optional<Cookie> authCookie = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .findFirst();

        authCookie.ifPresent(cookie -> cookie.setMaxAge(0));

        return ResponseEntity.noContent().build();
    }
}
