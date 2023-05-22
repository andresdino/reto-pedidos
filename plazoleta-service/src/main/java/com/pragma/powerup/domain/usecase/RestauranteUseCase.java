package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestauranteServicePort;
import com.pragma.powerup.domain.model.RestauranteModel;
import com.pragma.powerup.domain.spi.persistence.IRestuarantePersistencePort;

public class RestauranteUseCase implements IRestauranteServicePort {

    private final IRestuarantePersistencePort restuarantePersistencePort;

    public RestauranteUseCase(IRestuarantePersistencePort restuarantePersistencePort) {
        this.restuarantePersistencePort = restuarantePersistencePort;
    }


    @Override
    public void saveRestaurant(RestauranteModel restauranteModel) {
        restuarantePersistencePort.saveRestaurante(restauranteModel);
    }
}
