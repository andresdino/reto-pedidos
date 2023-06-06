package com.pragma.powerup.domain.spi.persistence;

import com.pragma.powerup.domain.model.User;

import java.util.List;

public interface IUserPersistencePort {
    User saveUser(User user);

    User getUserById(Long id);

    User getUserByCorreo(String correo);

    Boolean existsUserById(Long id);

    List<User> getAllUser();

    void deleteUserById(Long id);
}
