package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserRequestDTO;
import com.pragma.powerup.application.dto.response.UserResponseDTO;


import java.util.List;

public interface IUserHandler {

    void saveUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserById(Long id);

    List<UserResponseDTO> getAllUser();

}
