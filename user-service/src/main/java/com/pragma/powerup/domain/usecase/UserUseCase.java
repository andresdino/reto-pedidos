package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.persistence.IUserPersistencePort;

import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public void saveUser(User user) {

    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }
}
