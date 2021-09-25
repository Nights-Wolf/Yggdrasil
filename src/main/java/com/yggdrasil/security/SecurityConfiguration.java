package com.yggdrasil.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/user/**").hasAuthority(ApplicationPermissions.USER_DELETE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/user/**").hasAuthority(ApplicationPermissions.USER_EDIT.getPermission())
                .antMatchers(HttpMethod.GET, "/api/user/**").hasAuthority(ApplicationPermissions.USER_GET.getPermission())
                .antMatchers(HttpMethod.POST, "/api/transaction/**").hasAuthority(ApplicationPermissions.TRANSACTION_ADD.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/transaction/**").hasAuthority(ApplicationPermissions.TRANSACTION_DELETE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/transaction/**").hasAuthority(ApplicationPermissions.TRANSACTION_EDIT.getPermission())
                .antMatchers(HttpMethod.POST, "/api/category/**").hasAuthority(ApplicationPermissions.CATEGORY_ADD.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/category/**").hasAuthority(ApplicationPermissions.CATEGORY_DELETE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/category/**").hasAuthority(ApplicationPermissions.CATEGORY_EDIT.getPermission())
                .antMatchers(HttpMethod.POST, "/api/item/**").hasAuthority(ApplicationPermissions.ITEM_ADD.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/item/**").hasAuthority(ApplicationPermissions.ITEM_DELETE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/item/**").hasAuthority(ApplicationPermissions.ITEM_EDIT.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
