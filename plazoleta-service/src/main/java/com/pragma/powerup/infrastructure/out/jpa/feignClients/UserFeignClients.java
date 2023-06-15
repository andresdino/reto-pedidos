package com.pragma.powerup.infrastructure.out.jpa.feignClients;

import com.pragma.powerup.infrastructure.out.jpa.feignClients.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "localhost:8078/api/v1/user")
public interface UserFeignClients {

    @GetMapping("/existsUserById/{id}")
    Boolean existsUserById(@PathVariable(value = "id") Long usuarioId);

    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable(value = "id") Long usuarioId);

    @GetMapping("/email/{email}")
    UserDTO getUserByCorreo(@PathVariable(value = "email") String correo);
}