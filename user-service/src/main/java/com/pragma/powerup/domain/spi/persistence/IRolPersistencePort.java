package com.pragma.powerup.domain.spi.persistence;

import com.pragma.powerup.domain.model.Rol;

import java.util.List;

public interface IRolPersistencePort {

    Rol saveRol(Rol rol);
    Rol getRolId(Long id);

    List<Rol> getAllRol();

    void deleteRolById(Long id);

}
