package com.pragma.powerup.infrastructure.out.jpa.feignClients.adapter;



import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.feignClients.IUserFeignClientPort;
import com.pragma.powerup.infrastructure.out.jpa.feignClients.UserFeignClients;
import com.pragma.powerup.infrastructure.out.jpa.feignClients.dto.UserDTO;
import com.pragma.powerup.infrastructure.out.jpa.feignClients.mapper.IUserDTOMapper;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserFeignAdapter implements IUserFeignClientPort {

    private final UserFeignClients userFeignClients;

    private  final IUserDTOMapper userDTOMapper;

    @Override
    public Boolean existsUserById(Long usuarioId) {
        return userFeignClients.existsUserById(usuarioId);
    }

    @Override
    public User getUserById(Long usuarioId) {
        UserDTO userDTO =userFeignClients.getUserById(usuarioId);
        return userDTOMapper.toUser(userDTO);
    }

    @Override
    public User getUserByCorreo(String correo) {
        UserDTO userDTO= userFeignClients.getUserByCorreo(correo);
        return userDTOMapper.toUser(userDTO);
    }
}