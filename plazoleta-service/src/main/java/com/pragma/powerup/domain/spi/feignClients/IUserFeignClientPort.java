package com.pragma.powerup.domain.spi.feignClients;

import com.pragma.powerup.domain.model.User;

public interface IUserFeignClientPort {

    Boolean existsUserById(Long usuarioId);

    User getUserById(Long usuarioId);

    User getUserByCorreo(String correo);

}
