package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Plato;

import java.util.List;

public interface IPlatoServicePort {

    void savePlato(Plato plato);

    Plato getPlatoById(Long id);

    List<Plato> getAllPlatos();

    void deletePlatoById(Long id);

}
