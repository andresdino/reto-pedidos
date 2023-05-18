package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRolServicePort;
import com.pragma.powerup.domain.model.Rol;
import com.pragma.powerup.domain.spi.persistence.IRolPersistencePort;

import java.util.List;

public class RolUseCase implements IRolServicePort {

    private final IRolPersistencePort rolPersistencePort;

    public RolUseCase(IRolPersistencePort rolPersistencePort) {
        this.rolPersistencePort = rolPersistencePort;
    }

    @Override
    public void saveRol(Rol rol) {

    }

    @Override
    public Rol getRolById(Long id) {
        return null;
    }

    @Override
    public List<Rol> getAllRol() {
        return null;
    }

    @Override
    public void deleteRolById(Long id) {

    }
}
