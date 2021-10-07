package com.yggdrasil.security;

import lombok.Getter;

@Getter
public enum ApplicationPermissions {
    USER_DELETE("user:delete"),
    USER_EDIT("user:edit"),
    USER_GET("user:get"),
    ITEM_ADD("item:add"),
    ITEM_DELETE("item:delete"),
    ITEM_EDIT("item:edit"),
    TRANSACTION_ADD("transaction:add"),
    TRANSACTION_DELETE("transaction:delete"),
    TRANSACTION_EDIT("transaction:edit"),
    CATEGORY_ADD("category:add"),
    CATEGORY_EDIT("category:edit"),
    CATEGORY_DELETE("category:delete");

    private final String permission;

    ApplicationPermissions(String permission) {
        this.permission = permission;
    }
}
