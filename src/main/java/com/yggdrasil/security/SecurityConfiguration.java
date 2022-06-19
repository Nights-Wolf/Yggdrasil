package com.yggdrasil.security;

import com.yggdrasil.service.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;

import static com.yggdrasil.security.ApplicationPermissions.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserDetailsService applicationUserDetailsService;

    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder, ApplicationUserDetailsService applicationUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserDetailsService = applicationUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/", "index", "template", "/css/*", "/js/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/user/**").hasAuthority(USER_DELETE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/user/**").hasAuthority(USER_EDIT.getPermission())
                .antMatchers(HttpMethod.GET, "/api/user/**").hasAuthority(USER_GET.getPermission())
                .antMatchers(HttpMethod.POST, "/api/transaction/**").hasAuthority(TRANSACTION_ADD.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/transaction/**").hasAuthority(TRANSACTION_DELETE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/transaction/**").hasAuthority(TRANSACTION_EDIT.getPermission())
                .antMatchers(HttpMethod.POST, "/api/category/**").hasAuthority(CATEGORY_ADD.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/category/**").hasAuthority(CATEGORY_DELETE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/category/**").hasAuthority(CATEGORY_EDIT.getPermission())
                .antMatchers(HttpMethod.POST, "/api/item/**").hasAuthority(ITEM_ADD.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/item/**").hasAuthority(ITEM_DELETE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/item/**").hasAuthority(ITEM_EDIT.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/user/promote/**").hasAuthority(PROMOTE_ADMIN.getPermission())
                .anyRequest()
                .authenticated();
              /**  .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/main", true)
                .and()
                .rememberMe().tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
                .key("Secret")
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login"); **/
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserDetailsService);
        return provider;
    }
}
