package com.yggdrasil.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.yggdrasil.security.ApplicationPermissions.*;

@Getter
public enum ApplicationRoles {

    ADMIN(Sets.newHashSet(CATEGORY_ADD,
            CATEGORY_DELETE,
            CATEGORY_EDIT,
            ITEM_ADD,
            ITEM_DELETE,
            ITEM_EDIT,
            TRANSACTION_ADD,
            TRANSACTION_DELETE,
            TRANSACTION_EDIT,
            USER_DELETE,
            USER_EDIT,
            USER_GET)),
    USER(Sets.newHashSet(TRANSACTION_ADD,
            TRANSACTION_DELETE,
            TRANSACTION_EDIT,
            USER_DELETE,
            USER_EDIT));

    private final Set<ApplicationPermissions> permission;

    ApplicationRoles(Set<ApplicationPermissions> permission) {
        this.permission = permission;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermission().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
