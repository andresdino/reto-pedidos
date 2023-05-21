package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Rol;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public interface IRolServicePort {

    void saveRol(Rol rol);

    Rol getRolById(Long id);

    List<Rol> getAllRol();

    void deleteRolById(Long id);



}
