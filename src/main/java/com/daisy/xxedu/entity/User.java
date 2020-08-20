package com.daisy.xxedu.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Data
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@RequiredArgsConstructor
@Component
public class User implements UserDetails {
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private Long teacherId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role;
        if(teacherId != null) {
            role = "ROLE_USER";
        } else {
            role = "ROLE_ADMIN";
        }
        return Arrays.asList(new SimpleGrantedAuthority(role));
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
