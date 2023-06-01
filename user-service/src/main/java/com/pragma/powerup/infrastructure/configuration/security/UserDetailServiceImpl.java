package com.pragma.powerup.infrastructure.configuration.security;

import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUSerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUSerRepository iuSerRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        UserEntity userEntity = iuSerRepository
                .findOneByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con este correo " + correo + " no existe."));

        return new UserDetailsImpl(userEntity);
    }
}
