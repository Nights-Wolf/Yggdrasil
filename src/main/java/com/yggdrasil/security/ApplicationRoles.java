package com.yggdrasil.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum ApplicationRoles {

    ADMIN(Sets.newHashSet(ApplicationPermissions.USER_GET, ApplicationPermissions.USER_EDIT, ApplicationPermissions.USER_DELETE,
            ApplicationPermissions.CATEGORY_ADD, ApplicationPermissions.CATEGORY_EDIT, ApplicationPermissions.CATEGORY_DELETE,
            ApplicationPermissions.ITEM_ADD, ApplicationPermissions.ITEM_EDIT, ApplicationPermissions.ITEM_DELETE,
            ApplicationPermissions.TRANSACTION_ADD, ApplicationPermissions.TRANSACTION_DELETE, ApplicationPermissions.TRANSACTION_EDIT)),
    USER(Sets.newHashSet(ApplicationPermissions.USER_EDIT, ApplicationPermissions.USER_DELETE,
            ApplicationPermissions.TRANSACTION_ADD, ApplicationPermissions.TRANSACTION_DELETE, ApplicationPermissions.TRANSACTION_EDIT));

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
