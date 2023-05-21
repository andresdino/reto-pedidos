package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.RolRequestDTO;
import com.pragma.powerup.application.dto.response.RolResponseDTO;

import java.util.List;

public interface IRolHandler {

    void saveRol(RolRequestDTO rolRequestDTO);


   RolResponseDTO getRolById(Long id);

   List<RolResponseDTO> getAllRol();

   void deleteRolByid(Long id);




}
