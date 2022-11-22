package com.yggdrasil.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.yggdrasil.security.ApplicationRoles;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Users.class)
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

    private String city;

    private String voivodeship;
    private Long refreshToken;
    private boolean rememberMe;
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
                 String city, String voivodeship, Long refreshToken, boolean rememberMe, boolean acceptedTerms, boolean acceptedRodo, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
                 boolean isEnabled, String grantedAuthorities) {
        this.id = id;
        this.grantedAuthorities = grantedAuthorities;
        this.username = username;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.voivodeship = voivodeship;
        this.refreshToken = refreshToken;
        this.rememberMe = rememberMe;
        this.acceptedTerms = acceptedTerms;
        this.acceptedRodo = acceptedRodo;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }
}
