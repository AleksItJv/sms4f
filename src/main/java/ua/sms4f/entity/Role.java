package ua.sms4f.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    GUEST, USER, ADMIN, SUPERADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}