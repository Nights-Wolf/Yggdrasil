package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
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
        return userDatabase.findByEmail(email);
    }
}
