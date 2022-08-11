package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.UserDatabase;
import com.yggdrasil.model.Users;
import com.yggdrasil.security.ApplicationRoles;
import com.yggdrasil.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserDatabase userDatabase;

    @Autowired
    public ApplicationUserDetailsService(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userDatabase.findByEmail(email);
        return new User(users.getEmail(), users.getPassword(), users.isEnabled(), users.isAccountNonExpired(),
                users.isCredentialsNonExpired(), users.isAccountNonLocked(), ApplicationRoles.USER.getGrantedAuthorities());
    }
}
