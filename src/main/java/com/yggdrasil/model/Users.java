package com.yggdrasil.model;

import com.yggdrasil.security.ApplicationRoles;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

@Entity
@Setter
@Getter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String grantedAuthorities;
    private String username;

    private String surname;
    private String password;
    private String email;
    private String street;
    private String zipCode;

    private String voivodeship;

    private Long refreshToken;

    private boolean acceptedTerms;

    private boolean acceptedRodo;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public Users() {
        super();
    }

    public Users(Long id, String username, String surname, String password, String email, String street, String zipCode,
                 String voivodeship, Long refreshToken, boolean acceptedTerms, boolean acceptedRodo, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
                 boolean isEnabled, String grantedAuthorities) {
        this.id = id;
        this.grantedAuthorities = grantedAuthorities;
        this.username = username;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.street = street;
        this.zipCode = zipCode;
        this.voivodeship = voivodeship;
        this.refreshToken = refreshToken;
        this.acceptedTerms = acceptedTerms;
        this.acceptedRodo = acceptedRodo;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }
}
