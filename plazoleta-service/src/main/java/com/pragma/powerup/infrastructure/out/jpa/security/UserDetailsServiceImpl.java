package com.pragma.powerup.infrastructure.out.jpa.security;

import com.pragma.powerup.infrastructure.out.jpa.feignClients.UserFeignClients;
import com.pragma.powerup.infrastructure.out.jpa.feignClients.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private  final UserFeignClients userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDTO userDTO = userFeignClient.getUserByCorreo(email);
        return new UserDetailsImpl(userDTO);
    }
}
