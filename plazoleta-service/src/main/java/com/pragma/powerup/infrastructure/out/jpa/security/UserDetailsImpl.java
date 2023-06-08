package com.pragma.powerup.infrastructure.out.jpa.security;

import com.pragma.powerup.infrastructure.out.jpa.feignClients.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final UserDTO userDTO;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userDTO.getRol().getNombre()));
    }

    @Override
    public String getPassword() {
        return userDTO.getClave();
    }

    @Override
    public String getUsername() {
        return userDTO.getCorreo();
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

    public String getNombre(){
        return userDTO.getNombre();
    }

    public Long getId(){
        return userDTO.getId();
    }
}
