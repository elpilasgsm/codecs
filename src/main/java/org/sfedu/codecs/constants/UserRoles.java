package org.sfedu.codecs.constants;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
    ADMIN,
    USER,
    MODERATOR;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
