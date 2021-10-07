package com.yggdrasil.model;

import com.yggdrasil.security.ApplicationRoles;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.PushBuilder;
import java.util.Collection;

@Entity
@Setter
@Getter
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String grantedAuthorities;
    private String username;
    private String password;
    private String email;
    private String street;
    private String zipCode;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public Users() {
        super();
    }

    public Users(Long id, String username, String password, String email, String street, String zipCode,
                 boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
                 boolean isEnabled, String grantedAuthorities) {
        this.id = id;
        this.grantedAuthorities = grantedAuthorities;
        this.username = username;
        this.password = password;
        this.email = email;
        this.street = street;
        this.zipCode = zipCode;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ApplicationRoles.USER.getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
