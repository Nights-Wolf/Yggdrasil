package com.yggdrasil.security;

import com.yggdrasil.datasource.PostgresDatasource;
import com.yggdrasil.service.ApplicationUserDetailsService;
import com.yggdrasil.service.RefreshTokenService;
import com.yggdrasil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.yggdrasil.security.ApplicationPermissions.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserDetailsService applicationUserDetailsService;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final String secret;

    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder, ApplicationUserDetailsService applicationUserDetailsService,
                                 RefreshTokenService refreshTokenService, UserService userService, @Value("$(jwt.secret)") String secret) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserDetailsService = applicationUserDetailsService;
        this.refreshTokenService = refreshTokenService;
        this.userService = userService;
        this.secret = secret;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), refreshTokenService, userService, secret);
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(secret), UsernamePasswordAuthenticationFilter.class);
        http.cors();
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/login", "/", "/about/**", "/terms/**", "/contact/**", "/rodo/**", "/register/**", "/remindPassword/**", "/forgotPassword", "/resetPasswordEmailSent/**", "/products/**", "/passResetExpired", "/resetExpired", "/*", "index", "template", "/css/**", "/js/**", "/main**", "/vendor**", "/imgs/**", "/favicon**", "/editProfile", "/product/**", "/summary/**").permitAll()
                .antMatchers("/api/login/**", "/api/authentication/signOut/**", "/remindPassword/**").permitAll()
                .antMatchers("/api/shipments/**", "/api/cart/**", "/api/payment/**").permitAll()
                //.antMatchers("/editProfile/**").hasAuthority(USER_EDIT.getPermission())
                .antMatchers(HttpMethod.POST, "/api/mail/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/authentication/refresh/token/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/resetPasswordToken/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/resetPasswordToken/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/user/**").hasAuthority(USER_DELETE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/order/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/order/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/order/**").hasAuthority(TRANSACTION_DELETE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/order/**").hasAuthority(TRANSACTION_EDIT.getPermission())
                .antMatchers(HttpMethod.POST, "/api/category/**").hasAuthority(CATEGORY_ADD.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/category/**").hasAuthority(CATEGORY_DELETE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/category/**").hasAuthority(CATEGORY_EDIT.getPermission())
                .antMatchers(HttpMethod.POST, "/api/item/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/item/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/item/**").hasAuthority(ITEM_DELETE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/item/**").hasAuthority(ITEM_EDIT.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/user/promote/**").hasAuthority(PROMOTE_ADMIN.getPermission())
                .anyRequest().authenticated();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserDetailsService);
        return provider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
