package com.yggdrasil.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yggdrasil.model.Users;
import com.yggdrasil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/user")

public class UserRestController {


    private UserService userService;
    private String secret;

    @Autowired
    public UserRestController(UserService userService, @Value("$(jwt.secret)") String secret) {
        this.userService = userService;
        this.secret = secret;
    }

    @PostMapping("/create")
     private void createUser(@RequestBody Users users) {
        userService.createUser(users);
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
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withClaim("ROLES", users.getGrantedAuthorities())
                    .sign(Algorithm.HMAC256(secret));
            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", access_token);
            tokens.put("refresh_token", refresh_Token);
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);
        } catch (Exception exception) {

            response.setHeader("error", exception.getMessage());
        }
    } else {
        throw new RuntimeException("Refresh token is missing");
    }
    }

    @PostMapping("/signOut")
    public ResponseEntity<Void> signOut(HttpServletRequest request) {
        SecurityContextHolder.clearContext();

        Optional<Cookie> authCookie = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .findFirst();

        authCookie.ifPresent(cookie -> cookie.setMaxAge(0));

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    private void getUser(@PathVariable("id") Long id) {
        userService.getUser(id);
    }

    @PutMapping("{id}")
    private void editUser(@PathVariable("id") Long id, @RequestBody Users users) {
        userService.editUser(id, users);
    }

    @DeleteMapping("{id}")
    private void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @RequestMapping(value = "/promote/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    private void grantAdmin(@PathVariable("id") Long id) {
        userService.grantAdmin(id);
    }
}
