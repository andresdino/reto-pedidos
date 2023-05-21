package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RolRequestDTO;
import com.pragma.powerup.application.dto.response.RolResponseDTO;
import com.pragma.powerup.application.handler.IRolHandler;

import com.pragma.powerup.application.mapper.request.IRolRequestMapper;
import com.pragma.powerup.application.mapper.response.IRolResponseMapper;
import com.pragma.powerup.domain.api.IRolServicePort;
import com.pragma.powerup.domain.model.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@RequiredArgsConstructor
@Service
@Transactional
public class RolHandler implements IRolHandler {



    private final  IRolServicePort rolServicePort;
    private final IRolResponseMapper rolResponseMapper;
    private final IRolRequestMapper rolRequestMapper;


    @Override
    public void saveRol(RolRequestDTO rolRequestDTO) {

        Rol rol = rolRequestMapper.toRol(rolRequestDTO);
        rolServicePort.saveRol(rol);

    }

    @Override
    public RolResponseDTO getRolById(Long id) {
        RolResponseDTO rolResponseDto = rolResponseMapper.toResponse(rolServicePort.getRolById(id));
        return rolResponseDto;
    }

    @Override
    public List<RolResponseDTO> getAllRol() {
        return rolResponseMapper.toResponsList(rolServicePort.getAllRol());

    }

    @Override
    public void deleteRolByid(Long id) {
         rolServicePort.deleteRolById(id);
    }
}
