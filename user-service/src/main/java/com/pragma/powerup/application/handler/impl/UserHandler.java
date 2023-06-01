package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDTO;
import com.pragma.powerup.application.dto.response.UserResponseDTO;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.request.IUserRequestMapper;
import com.pragma.powerup.application.mapper.response.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserResponseMapper userResponseMapper;
    private final IUserRequestMapper userRequestMapper;

    @Override
    public void saveUser(UserRequestDTO userRequestDTO) {
        User user = userRequestMapper.toUSer(userRequestDTO);
        userServicePort.saveUser(user);
    }



    @Override
    public UserResponseDTO getUserById(Long id) {
        UserResponseDTO userResponseDTO = userResponseMapper.toResponse(userServicePort.getUserById(id));
        return userResponseDTO;

    }

    @Override
    public List<UserResponseDTO> getAllUser() {
        return userResponseMapper.toResponseList(userServicePort.getAllUser());
    }
}
