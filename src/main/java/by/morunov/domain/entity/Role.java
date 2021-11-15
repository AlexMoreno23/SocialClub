package by.morunov.domain.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Alex Morunov
 */
public enum Role implements GrantedAuthority {
    PERSON,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
