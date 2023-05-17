package com.pragma.powerup.domain.usecase.persistence;

import com.pragma.powerup.domain.model.User;

import java.util.List;

public interface IUserPersistencePort {
    User saveUser(User user);

    User getUserById(Long id);

    List<User> getAllUser();
}
