package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Rol;

import java.util.List;
public interface IRolServicePort {

    void saveRol(Rol rol);

    Rol getRolById(Long id);

    List<Rol> getAllRol();

    void deleteRolById(Long id);



}
