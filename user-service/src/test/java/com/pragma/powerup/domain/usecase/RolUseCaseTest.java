package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.Rol;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.password.IUserPassEncryptPort;
import com.pragma.powerup.domain.spi.persistence.IRolPersistencePort;
import com.pragma.powerup.domain.spi.persistence.IUserPersistencePort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RolUseCaseTest {

    @Mock
    IRolPersistencePort iRolPersistencePort;
    @InjectMocks
    Rol rol;
    @InjectMocks
    RolUseCase rolUseCase;

    Rol rol1 = new Rol();

    @BeforeEach
    void setUp() {
        rol1.setNombre("carlos");
        rol1.setDescripcion("gerente");
        Mockito.when(iRolPersistencePort.getRolId(1L)).thenReturn(rol1);

        List<Rol> clasRolList = new ArrayList<>();
        clasRolList.add(rol1);
        Mockito.when(iRolPersistencePort.getAllRol()).thenReturn(clasRolList);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveRol() {
    }

    @Test
    void getRolById() {
        var data = rolUseCase.getRolById(1L);
    }

    @Test
    void getAllRol() {
        var data = rolUseCase.getAllRol();
    }

    @Test
    void deleteRolById() {
    }
}