package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.password.IUserPassEncryptPort;
import com.pragma.powerup.domain.spi.persistence.IUserPersistencePort;

import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IUserPassEncryptPort userPassEncryptPort;



    public UserUseCase(IUserPersistencePort userPersistencePort, IUserPassEncryptPort userPassEncryptPort) {
        this.userPersistencePort = userPersistencePort;
        this.userPassEncryptPort = userPassEncryptPort;
    }


    @Override
    public void saveUser(User user) {

        user.setClave(userPassEncryptPort.encode(user.getClave()));
        userPersistencePort.saveUser(user);
    }


    @Override
    public User getUserById(Long id) {
        return userPersistencePort.getUserById(id);
    }

    @Override
    public List<User> getAllUser() {

        return userPersistencePort.getAllUser();
    }
}
