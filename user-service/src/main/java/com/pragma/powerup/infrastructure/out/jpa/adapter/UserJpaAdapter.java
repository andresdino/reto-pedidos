package com.pragma.powerup.infrastructure.out.jpa.adapter;


import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.persistence.IUserPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUSerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter  implements IUserPersistencePort {

    private final IUSerRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(user));
        return userEntityMapper.toUserModel(userEntity);

    }

    @Override
    public User getUserById(Long id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        UserEntity userEntity = optionalUserEntity.orElse(null);
        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public User getUserByCorreo(String correo) {
        return null;
    }

    @Override
    public Boolean existsUserById(Long id) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        List<UserEntity> userEntityList =  userRepository.findAll();
        if(userEntityList.isEmpty()){
            throw new NoDataFoundException();
        }

        return userEntityMapper.toUserModelList(userEntityList);
    }

    @Override
    public void deleteUserById(Long id) {

    }
}
