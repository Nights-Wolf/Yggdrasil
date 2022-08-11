package com.yggdrasil.security;

import com.yggdrasil.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetails implements UserDetails {

   private final Users users;

   @Autowired
    public MyUserDetails(Users users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(users.getGrantedAuthorities().equals("USER")) {
            return ApplicationRoles.USER.getGrantedAuthorities();
        }
        return ApplicationRoles.ADMIN.getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return users.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return users.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return users.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return users.isEnabled();
    }
}
