package com.pragma.powerup.domain.spi.persistence;

import com.pragma.powerup.domain.model.RestauranteModel;

public interface IRestuarantePersistencePort {

    RestauranteModel saveRestaurante(RestauranteModel restauranteModel);
}
