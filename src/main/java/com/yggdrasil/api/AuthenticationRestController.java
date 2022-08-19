package com.yggdrasil.api;

import com.yggdrasil.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;
    private final String secret;

    public AuthenticationRestController(AuthenticationService authenticationService, @Value("$(jwt.secret)") String secret) {
        this.authenticationService = authenticationService;
        this.secret = secret;
    }

    @GetMapping("/refresh/token")
    private void refreshToken(HttpServletRequest request, HttpServletResponse response, String secret) throws IOException {
        authenticationService.refreshToken(request, response, secret);
    }

    @PostMapping("/signOut")
    public ResponseEntity<Void> signOut(HttpServletRequest request) {
        SecurityContextHolder.clearContext();

        Optional<Cookie> authCookie = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .findFirst();

        authCookie.ifPresent(cookie -> cookie.setMaxAge(0));

        return ResponseEntity.noContent().build();
    }
}
