package com.pragma.powerup.domain.spi.feing;

import com.pragma.powerup.domain.model.RestaurantModel;

public interface IClientFeignPort {

    RestaurantModel getRestaurantByIdPropietario(Long idPropietario);
}
