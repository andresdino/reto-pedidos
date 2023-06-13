package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserRequestDTO;
import com.pragma.powerup.application.dto.response.UserResponseDTO;


import java.util.List;

public interface IUserHandler {

    void saveUser(UserRequestDTO userRequestDTO);

    void saveRestaurantEmployee(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserById(Long id);

    UserResponseDTO getUserByCorreo(String correo);

    Boolean existsUserById(Long id);

    List<UserResponseDTO> getAllUsers();

    void deleteUserById(Long id);
}
