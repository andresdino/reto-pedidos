package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.Rol;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.password.IUserPassEncryptPort;
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

@SpringBootTest
class UserUseCaseTest {

    @Mock
    IUserPassEncryptPort iUserPassEncryptPortMock;
    @Mock
    IUserPersistencePort iUserPersistencePortMock;
    @InjectMocks
    User user;

    @InjectMocks
    UserUseCase userUseCase;

    User user1 = new User();

    @BeforeEach
    void setUp() {
        user1.setNombre("Calos");
        user1.setApellido("Garzon");
        user1.setDocumentoDeIdentidad("42234");
        user1.setCelular("43456");
        user1.setCorreo("sdvg@dsf");
        user1.setClave("123");
        user1.setId((long) 1);
        Mockito.when(iUserPersistencePortMock.getUserById(1L)).thenReturn(user1);

        List<User> clasUseList = new ArrayList<>();

        clasUseList.add(user1);
        Mockito.when(iUserPersistencePortMock.getAllUser()).thenReturn(clasUseList);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void getUserById() {

        var data = userUseCase.getUserById(1L);
    }

    @Test
    void getAllUser() {
        var data = userUseCase.getAllUser();
    }
}