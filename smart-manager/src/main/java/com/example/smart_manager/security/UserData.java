package com.example.smart_manager.security;

import com.example.smart_manager.user.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserData implements UserDetails {
    private UUID userId;
    private String email;
    private String password;
    private UserRole userRole;
    private boolean isAccountActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isAccountActive;
    }

    @Override
    public boolean isEnabled() {
        return isAccountActive;
    }
}
