package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IPlatoServicePort;
import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.model.Plato;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.persistence.IPlatoPersistencePort;
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
class PlatoUseCaseTest {

    @Mock
    IPlatoPersistencePort iPlatoPersistencePortMock;

    @InjectMocks
    Plato plato = new Plato();
    @InjectMocks
    Categoria categoria;

    @InjectMocks
    Restaurant restaurant;

    @InjectMocks
    PlatoUseCase platoUseCase;



    @BeforeEach
    void setUp() {
        plato.setNombre("Bandeja paisa");
        plato.setPrecio("20000");
        plato.setDescripcion("bandeja con sop y jugo");
        plato.setUrlImagen("");
        plato.setActivo(true);
        plato.setRestauranteId(restaurant);
        plato.setCategoriaId(categoria);
        Mockito.when(iPlatoPersistencePortMock.getPlatoById(1L)).thenReturn(plato);

        List<Plato> clasPlatoList = new ArrayList<>();

        clasPlatoList.add(plato);
        Mockito.when(iPlatoPersistencePortMock.getAllPlato()).thenReturn(clasPlatoList);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPlatoById() {
        var data = platoUseCase.getPlatoById(1L);
    }

    @Test
    void getAllPlatos() {
    }
}